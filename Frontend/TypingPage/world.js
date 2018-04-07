
// bootstrap app to the loading of the page (it doesn't work as shown in vue their examples)

var url = "http://localhost:8080/words"
window.onload = function() {
    var app = new Vue({
        el: "#app",
        data: {
            message: "Hello World!"
        },
        methods: {
            loadWords: function () {
                // Ping the server for some sweet words!
                fetch(url, {
                    headers: {
                        'content-type' : 'application/json'
                    },
                    method: "get",
                    mode: "cors"
                }).then(response => console.log(response));
            }
        },
        beforeMount() {
            this.loadWords();
        }
    });
}