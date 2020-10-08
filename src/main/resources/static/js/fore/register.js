$(function(){
    const data4Vue = {
        uri:'/auth/register',
        message: '',
        show: false,
        user:{userName:'',password:'','checkPassword':''}
    };
    //ViewModel
    const vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted:function(){ //mounted　表示这个 Vue 对象加载成功了

        },
        methods: {
            register: function () {
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
                if (isEmpty(this.user.checkPassword)) {
                    vue.message = "\"验证密码\"不能为空!";
                    vue.show = true;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                    return;
                }
                if (this.user.password != this.user.checkPassword) {
                    vue.message = "\"密码\"输入不一致!";
                    vue.show = true;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                    return;
                }

                const url = this.uri;
                axios.post(url,this.user).then(function(response){
                    if (response.data.code == 6004) {
                        vue.show = true;
                        vue.message = "注册成功！ 正在跳转登录页面...";
                        setTimeout(function () {
                            vue.show = false;
                            location.href="/c/login";
                        }, 3000)
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