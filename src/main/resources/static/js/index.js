var messageApi = Vue.resource('/message{/id}');

function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i] === id) {
            return i;
        }else{
        	return -1;
        }
    }
}

Vue.component('message-form',{
	props: ['messages','message'],
	data: function(){
		return {
			text: '',
			id:''
		}
	},
	watch:{
		message: function(newVal,oldVal) {
			this.text = newVal.text;
			this.id = newVal.id;
		}
	},
	template:
		`<div>
			<input type="text" placeholder="Enter message" v-model="text"/>
			<input type="button" value="Save" @click="save"/>
		</div>`,
	methods:{
		save: function() {
			let message = {text: this.text};
			if(this.id){
				messageApi.update({id: this.id}, message).then(result =>
					result.json().then(data =>{
						let index = getIndex(this.messages, data.id)
						this.messages.splice(index, 1, data);
						this.text = '';
						this.id = '';
					})
				)
			}else{
				messageApi.save({}, message).then(result =>
					result.json().then(data =>{
						this.messages.push(data);
						this.text='';
					})
				)
			}
			
		}
	}	
		
});
Vue.component('message-row',{
    props:['message', 'editMessage'],
    template:
    	`<div>
    		<i>{{message.id}}:</i> {{message.text}}
    		<span>
    			<input type="button" value="Edit" @click="edit" />
    		</span>	
    	</div>`,
    methods:{
    	edit: function() {
			this.editMessage(this.message);
		}
    }	
});
Vue.component('messages-list', {
    props:['messages'],
    data: function() {
		return{
			message: null
		}
	},
    template: 
    	`<div>
    		<message-form :messages="messages" :message="message"/>
    		<message-row v-for="message in messages" :key="message.id" :message="message" :editMessage="editMessage"/>
    	</div>`,
    created: function () {
        messageApi.get().then(response => {
            response.json().then(data =>
                data.forEach(message =>this.messages.push(message))
            )
        }, response => {
            // error callback
        });

    },
    methods:{
    	editMessage: function(message) {
			this.message = message;
		}
    }
});
var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: []
    }
});