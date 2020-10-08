$(function(){
    const data4Vue = {
        uri:'/auth/login',
        message: '',
        show: false,
        user:{userName:'',password:'',rememberMe:false}
    };
    //ViewModel
    const vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted:function(){

        },
        methods: {
            login: function () {
                if (isEmpty(this.user.userName)) {
                    vue.message = "\"昵称\"不能为空!";
                    vue.show = true;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                    return;
                }
                if (isEmpty(this.user.password)) {
                    vue.message = "\"密码\"不能为空!";
                    vue.show = true;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                    return;
                }

                const url = this.uri;
                axios.post(url,this.user).then(function(response){
                    if (response.data.code == 6000) {
                        location.href = "/c/";
                    } else {
                        vue.show = true;
                        vue.message = response.data.msg;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                }).catch(function (error) {
                    vue.show = true;
                    vue.message = error;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                });
            },
            closeAlert: function() {
                vue.show = false;
            }
        }
    });
});