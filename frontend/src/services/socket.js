import SockJS from 'sockjs-client'
import Stomp from 'stompjs'


const url = `http://${window.location.host}/ws`;

let stompClient;
let subscriptions = [];

let notificationSubscription;

let stompRequests = [];

let socket = new SockJS(url);

let stompLoop = () => {
    setTimeout(() => {
        while (stompRequests.length > 0) {
            let request = stompRequests.shift();
            if (request.type === "unsubscribe all auction bids") {
                subscriptions.forEach(s => s.unsubscribe());
                subscriptions = [];
            } else if (request.type === "subscribe") {
                stompClient.subscribe(request.route, request.messageHandler);
            } else if (request.type === "unsubscribe notifications") {
                if (notificationSubscription !== null) {
                    notificationSubscription.unsubscribe();
                }
                notificationSubscription = null;
            }
        }
        stompLoop();
    }, 500)
};

stompClient = Stomp.over(socket);
stompClient.connect({}, stompLoop);
stompClient.debug = null;

export default () => {
    return {
        subscribeToAuctionBids(auctionId, messageHandler) {

            stompRequests.push({
                type: "subscribe",
                route: "/auctionBids/" + auctionId,
                messageHandler: messageHandler
            });
        },
        unsubscribeAllAuctionBids() {
            stompRequests.push({
                type: "unsubscribe all auction bids"
            });
        },
        unsubscribeNotifications() {
            stompRequests.push({
                type: "unsubscribe notifications"
            })
        },
        subscribeNotifications(messageHandler) {

            stompRequests.push({
                type: "subscribe",
                route: "/user/queue/notify",
                messageHandler: messageHandler
            });
        }
    }
}

