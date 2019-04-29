import SockJS from 'sockjs-client'
import Stomp from 'stompjs'


// todo research why proxy doesnt work with Stomp sockets

const url = `http://${window.location.host}/ws`;

// const url = 'http://localhost:8080/ws';

// let socket = new SockJS('http://localhost:8080/ws');

// let socket = new SockJS(url);
let stompClient;
let subscriptions = [];

export default () => {
    return {
        subscribeToAuctionBids(auctionId, messageHandler) {
            if (!stompClient) {
                let socket = new SockJS(url);
                stompClient = Stomp.over(socket);
                stompClient.connect({}, () => {
                    let subscription = stompClient.subscribe("/auctionBids/" + auctionId, messageHandler);
                    subscriptions.push(subscription);
                });
            } else {
                let subscription = stompClient.subscribe("/auctionBids/" + auctionId, messageHandler);
                subscriptions.push(subscription);
            }
        },
        unsubscribeAllAuctionBids() {
            subscriptions.forEach(s => s.unsubscribe());
        },
    }
}

