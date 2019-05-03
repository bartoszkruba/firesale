import SockJS from 'sockjs-client'
import Stomp from 'stompjs'


const url = `http://${window.location.host}/ws`;

let stompClient;
let subscriptions = [];

let notificationSubscription;
let chatSubscription;

let stompRequests = [];


let stompLoop = () => {
    setTimeout(() => {
        while (stompRequests.length > 0) {
            let request = stompRequests.shift();
            switch (request.type) {
                case 0:
                    subscriptions.push(stompClient.subscribe(request.route, request.messageHandler));
                    break;
                case 1:
                    subscriptions.forEach(s => s.unsubscribe());
                    subscriptions = [];
                    break;
                case 2:
                    if (notificationSubscription !== null) {
                        notificationSubscription.unsubscribe();
                        notificationSubscription = null;
                    }
                    notificationSubscription = null;
                    break;
                case 3:
                    if (notificationSubscription != null) {
                        notificationSubscription.unsubscribe();
                    }
                    notificationSubscription = stompClient.subscribe(request.route, request.messageHandler);
                    break;
                case 4:
                    if (chatSubscription != null) {
                        chatSubscription.unsubscribe();
                    }
                    chatSubscription = stompClient.subscribe(request.route, request.messageHandler);
                    break;
                case 5:
                    if (chatSubscription !== null) {
                        chatSubscription.unsubscribe();
                        chatSubscription = null;
                    }
                    break;
                case 6:
                    stompClient.send("/app/chat.sendMessage", {},
                        JSON.stringify(request.message));
                    break;
            }

        }
        stompLoop();
    }, 500)
};

let socket = new SockJS(url);
stompClient = Stomp.over(socket);
stompClient.connect({}, stompLoop);
stompClient.debug = null;

export default () => {
    return {
        subscribeToAuctionBids(auctionId, messageHandler) {

            stompRequests.push({
                type: 0,
                route: "/auctionBids/" + auctionId,
                messageHandler: messageHandler
            });
        },
        unsubscribeAllAuctionBids() {
            stompRequests.push({
                type: 1
            });
        },
        unsubscribeNotifications() {
            stompRequests.push({
                type: 2
            })
        },
        subscribeNotifications(messageHandler) {
            stompRequests.push({
                type: 3,
                route: "/user/queue/notifications",
                messageHandler: messageHandler
            });
        },
        subscribeChat(messageHandler) {
            stompRequests.push({
                type: 4,
                route: "/user/queue/chat/messages",
                messageHandler: messageHandler
            })
        },
        unsubscribeChat() {
            stompRequests.push({
                type: 5
            })
        },
        sendMessage(message) {
            stompRequests.push({
                type: 6,
                message: message
            })
        },
        reconnect(callback) {
            subscriptions.forEach(s => s.unsubscribe());
            subscriptions = [];
            if (notificationSubscription) {
                notificationSubscription.unsubscribe();
                notificationSubscription = null;
            }
            stompClient.disconnect();
            stompRequests = [];
            socket = new SockJS(url);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, callback);
            stompClient.debug = null;
        }
    }
}

