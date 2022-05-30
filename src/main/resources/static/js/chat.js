const url = 'http://localhost:8080';
let stompClient;
let selectedUser;

function connectToChat() {
    console.log("connecting to chat...")
    var userId = document.getElementById('fromId').value;
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/messages/" + userId, function (response) {
            let data = JSON.parse(response.body);
            render(data['context']);
        });
    });
}

function sendMsg(sendTo, text) {
     var conversationId = document.getElementById('conversationId').value;
     var senderId = document.getElementById('fromId').value;
    stompClient.send("/app/chat/" +sendTo+"/"+conversationId+"/"+senderId+"/"+text);
}



