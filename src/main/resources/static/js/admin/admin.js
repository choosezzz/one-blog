$(function(){
    var data4VueAll = {
        uriYesterday:'/count/yesterday',
        uriVisit:'/count/visit',
        uriUser:'/count/user',
        uriArticle:'/count/article',
        countAll: 0,
        countYesterday: 0,
        countUserAll: 0,
        countArticleAll: 0
    };
    //ViewModel
    var vue = new Vue({
        el: '#app',
        data: data4VueAll,
        mounted:function(){
            this.load();
        },
        methods: {
            load:function(){
                axios.get(this.uriVisit).then(function(response) {
                    vue.countAll = response.data.data;
                });
                axios.get(this.uriYesterday).then(function(response) {
                    vue.countYesterday = response.data.data;
                });
                axios.get(this.uriUser).then(function(response) {
                    vue.countUserAll = response.data.data;
                });
                axios.get(this.uriArticle).then(function(response) {
                    vue.countArticleAll = response.data.data;
                });
            }
        }
    });
});