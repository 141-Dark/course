<template>
    <div>
        <div class="row">
            <div class="col-md-6">
                <p>
                    <button @click="add1()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit"></i>
                        增加父节点
                    </button>
                    &emsp;
                    <button @click="all()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-refresh"></i>
                        刷新
                    </button>
                </p>

                <table id="level1-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>父节点</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level1" v-on:click="showLevel2(category)" v-bind:class="{'active':category.id == act.id}">
                        <td>{{category.id}}</td>
                        <td>{{category.parent}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">

                                <button @click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button @click="del(category.id)" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-warning">
                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                </button>
                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline pos-rel">
                                    <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                        <li>
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                                <span class="blue">
                                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                <span class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                <span class="red">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6">
                <p>
                    <button @click="add2()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit"></i>
                        增加子节点
                    </button>

                </p>

                <table id="level2-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level2">
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">

                                <button @click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button @click="del(category.id)" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-warning">
                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                </button>
                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline pos-rel">
                                    <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                        <li>
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                                <span class="blue">
                                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                <span class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                <span class="red">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Button trigger modal -->

        <!-- Modal -->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="form-horizontal">
                                <div class="form-group">
<!--                                    <label class="col-sm-2 control-label">id</label>-->
<!--                                    <div class="col-sm-10">-->
<!--                                        <input type="text" v-model="category.id" class="form-control" placeholder="id" name="categoryId">-->
<!--                                    </div>-->
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父分类</label>
                                    <div class="col-sm-10">
                                        <p class="form-control-static">{{act.name || "无"}}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="category.name" class="form-control" placeholder="名称" name="categoryName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">顺序</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="category.sort" class="form-control" placeholder="顺序" name="categorySort">
                                    </div>
                                </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button"  v-on:click="save()" class="btn btn-primary" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        //声明子组件
        components:{

        },
        name:"category",
        data:function () {
            return{
                categorys:[],
                category:{},
                level1:[],
                level2:[],
                act:{}
            }
        },
        mounted() {
            //调用父组件的方法
            //this.$parent.activeSlidebar("business-category-sidebar")
            let _this = this

            //开始周期时先执行这个方法
            _this.all()

        },
        methods:{
            //查询所有分类
            all(){
                let _this = this;
                //请求之前先加载等待框
                Loading.show()
                //查询每页之前对页面条数设置axios
                _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/category/all',
                    ).then((response)=>{
                    //关闭请求框
                    Loading.hide()

                    let resp = response.data;
                    _this.categorys = resp.content

                    //形成树形结构
                    _this.level1 = []//先清空
                    for(let i = 0;i<_this.categorys.length;i++){
                        let p = _this.categorys[i]
                        //判断parent是不是000，如果是则存到level1中(parent值为000代表的是没有父节点了)
                        if(p.parent == '000'){
                            _this.level1.push(p)
                            //从新遍历一次，找到与父节点id相同的节点，然后存入children中
                            for (let j = 0;j<_this.categorys.length;j++){
                                let child = _this.categorys[j]
                                if(child.parent == p.id){
                                    if(Tools.isEmpty(p.$children)){
                                        p.$children  = []
                                    }
                                    p.$children.push(child)
                                }
                            }
                        }
                    }

                    //触发一次点击事件，以刷新子节点,增加延时是因为渲染vue界面需要时间
                    _this.level2 = []
                    setTimeout(function () {
                        $("tr.active").trigger("click")
                    },100)
                })

            },


            //保存数据
            save(){
                let  _this  = this
                Loading.show()
                //前端校验
                $('#form-horizontal').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields:{
                        categoryName:{
                            validators:{
                                notEmpty:{
                                    message: "课程名不能为空"
                                }
                            }
                        },
                        courseId:{
                            validators:{
                                notEmpty:{
                                    message: "课程id不能为空"
                                },
                                stringLength: {
                                    min: 1,
                                    max: 8,
                                    message: '密码长度必须在1到8位之间'
                                },
                            }
                        }
                    }
                })
                let bootstrapValidator1 = $("#form-horizontal").data('bootstrapValidator');
                bootstrapValidator1.validate();

                if(!bootstrapValidator1.isValid()){
                    alert('请完善输入项');
                    return _this.all(1);
                }else {
                    _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/category/save',
                        _this.category).then((response)=>{
                        Loading.hide()
                        console.log("保存结果",response)

                        let resp = response.data
                        //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                        if(resp.success){
                            $("#form-modal").modal("hide")
                            //刷新列表
                            _this.all(1)
                            //调用自定义的Toast方法
                            Toast.success("操作成功！")
                        }else {
                            //弹出后端的提示框(这里的目的是检验如果没有前端校验，利用后端能否检验成功，这里成功了)
                            Toast.warning(resp.message)
                        }
                    })
                }
            },
            //父节点
            add1(){
                let _this = this
                //清空当前的act和level2
                _this.act = {}
                _this.level2 = []

                _this.category = {
                    parent:"000"
                }
                //$("#form-modal")中的modal是CSS选择器,modal()中的参数(show和hide)是内置的方法，用于弹出或关闭模态框
                $("#form-modal").modal("show")
            },//新增子节点
            add2(){
                let _this = this
                if(Tools.isEmpty(_this.act)){
                    Toast.warning("请先点击父节点")
                }
                _this.category={
                    parent:_this.act.id
                }
                $("#form-modal").modal("show")
            },
            //点击哪一行就将哪一行的数据代入编辑
            edit(category){
                let _this = this
                //解决vue双向绑定时不保存也会显示修改后的数据（并没有真正保存到数据库）的问题
                _this.category = $.extend({},category)//临时放到{}中
                $("#form-modal").modal("show")
            },

            //删除数据
            del(id){
                let  _this  = this
                Conform.show("你将无法恢复删除的内容",function () {
                    _this.ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/category/delete/'+id).then((response)=>{
                        console.log("删除结果",response)

                        let resp = response.data
                        //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                        if(resp.success){
                            //刷新列表
                            _this.all(1)
                            Toast.success("删除成功！")

                        }
                    })

                })
            },

            showLevel2(category){
                let _this = this
                _this.act = category//将点击过的category放到act中
                _this.level2 = category.$children
            }
        }
    }
</script>

<style scoped>
    .active td{
        background-color: #2a62bc !important;
    }
</style>