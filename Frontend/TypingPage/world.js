
// bootstrap app to the loading of the page (it doesn't work as shown in vue their examples)

var url = "http://localhost:8080/words";
var loadedWords = [];
window.onload = function() {
    var app = new Vue({
        el: "#app",
        data: {
            wordlist: "Hello World!"
        },
        methods: {
            loadWords: function () {
                // Ping the server for some sweet words!
                fetch(url, {
                    headers: {
                        'content-type': 'application/json'
                    },
                    method: "get",
                    mode: "cors"
                }).then(
                    response => response.json()
                ).then(arr =>{
                        // apperantly Jackson or Javascript removes the quotes.
                        loadedWords = arr;
                        this.wordlist = arr.join(" ");
                    }
                );
            }
        },
        beforeMount() {
            this.loadWords();
        }
    });
}