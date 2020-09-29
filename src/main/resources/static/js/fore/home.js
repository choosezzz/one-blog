$(function(){
    const data4Vue = {
        uri: '/c/user',
        message: '',
        show: false,
        user: {},
        file: ""
    };
    //ViewModel
    const vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted:function(){
            this.userInfo();
        },
        methods: {
            userInfo:function(){
                const url = this.uri + "/info";
                axios.get(url).then(function(response) {
                    if (response.data.code == 2001){
                        vue.user = response.data.data;
                        if (vue.user.birthday != null){
                            document.getElementById("doc-datepicker").value=vue.user.birthday;
                        }
                    }else {
                        vue.show = true;
                        vue.message = response.data.msg;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                });
            },
            update:function(){
                const url = this.uri+"/update";
                vue.user.birthday = document.getElementById("doc-datepicker").value;
                axios.post(url, vue.user).then(function(response) {
                    if (response.data.code == 2001) {
                        vue.show = true;
                        vue.message = "更新成功";
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    } else {
                        vue.show = true;
                        vue.message = response.data.msg;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                });
            },
            thisFace: function (event) {
                const url = this.uri + "/avatar";
                var formData = new FormData();
                formData.append("avatar", event.target.files[0]);
                axios.post(url, formData).then(function(response) {
                    if (response.data.code === 6007) {
                        vue.user.avatar = response.data.data;
                        vue.show = true;
                        vue.message = "上传成功";
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    } else {
                        vue.show = true;
                        vue.message = response.data.msg;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                });
            }
        }
    });
});