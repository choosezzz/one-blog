$(function(){
    const data4Vue = {
        uri:'/adm/user/',
        beans: [],
        roles:[],
        user: {userId:0,userName:"",status:0,roleId:0},
        pagination:{}
    };
    //ViewModel
    const vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted:function(){
            this.userList();
            this.roleList();
        },
        methods: {
            roleList:function(){
                const url =  this.uri + "/roles";
                axios.get(url).then(function(response) {
                    vue.roles = response.data.data;
                });
            },
            userList:function(){
                const url =  this.uri + "/list";
                axios.get(url).then(function(response) {
                    vue.pagination = response.data;
                    vue.beans = response.data.data;
                });
            },
            update:function (userId, status) {
                const url = this.uri + "/status";
                this.user.userId = userId;
                this.user.status = status;
                axios.post(url,vue.user).then(function(response){
                    if (response.data.id > 0) {
                        vue.list(vue.pagination.current-1);
                    } else {
                        alert("操作失败！");
                    }
                }).catch(function (error) {
                    alert(error);
                });
            },
            authorize: function (userId, event) {
                const url = this.uri + "/authorize";
                this.user.userId = userId;
                //change事件，取选中的value
                this.user.roleId = event.target.value;
                axios.post(url,vue.user).then(function(response){
                    if (response.data.data > 0) {
                        alert("修改权限成功！");
                    } else {
                        alert("操作失败！");
                    }
                }).catch(function (error) {
                    alert(error);
                });
            },
            jump: function(page){
                jump(page,vue); //定义在adminHeader.html 中
            },
            jumpByNumber: function(start){
                jumpByNumber(start,vue);
            }
        }
    });
});