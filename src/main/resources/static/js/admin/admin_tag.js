$(function(){
    const data4Vue = {
        uri:'/tag',
        countAll: 0,
        beans: [],
        message: '',
        show: false,
        tag: {},
        pagination:{}
    };
    //ViewModel
    const vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted:function(){ //mounted　表示这个 Vue 对象加载成功了
            this.list(0);
            this.count();
        },
        methods: {
            count:function(){
                const url = this.uri + "/count";
                axios.get(url).then(function(response) {
                    vue.countAll = response.data.data;
                });
            },
            list:function(start){
                const url =  this.uri+"/list";
                axios.get(url).then(function(response) {
                    // vue.pagination = response.data;
                    vue.beans = response.data.data;
                });
            },
            addBean: function(name) {
                const url = this.uri+"/add";
                this.tag.tagName=name;
                axios.post(url,this.tag).then(function(response){
                    if (response.data.code === 2001) {
                        vue.beans.push(response.data.data);
                    } else {
                        vue.message = response.data.msg;
                        vue.show = true;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                }).catch(function (error) {
                    vue.message = error;
                    vue.show = true;
                    setTimeout(function () {
                        vue.show = false;
                    }, 3000)
                });
            },
            deleteBean: function (id,name) {
                const url = this.uri+"/delete";
                this.tag.tagName = name;
                this.tag.tagId = id;
                axios.post(url, this.tag).then(function(response){
                    if(2001 === response.data.code) {
                        vue.list(0);
                        vue.count();
                    } else {
                        vue.message = response.data.msg;
                        vue.show = true;
                        setTimeout(function () {
                            vue.show = false;
                        }, 3000)
                    }
                });
            },
            closeAlert: function() {
                vue.show = false;
            },
            jump: function(page){
                jump(page,vue); //定义在adminHeader.html 中
            },
            jumpByNumber: function(start){
                jumpByNumber(start,vue);
            }
        }
    });

    $('#add-tag').on('click', function() {
        $('#my-prompt').modal({
            relatedElement: this,
            onConfirm: function() {
                vue.addBean(document.getElementById("name").value);
            }
        });
    });
});