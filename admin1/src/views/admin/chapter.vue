<template>
    <div>
        <h2>{{course.name}}</h2>
        <p>
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程
            </router-link>
            &emsp;<button @click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                增加
            </button>
            &emsp;
            <button @click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <pagination ref="pagination" v-bind:list="list" v-bind:item-count="8"></pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>名称</th>
                        <th>课程ID</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="chapter in chapters">
                        <td>{{chapter.id}}</td>
                        <td>{{chapter.name}}</td>
                        <td>{{chapter.courseId}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">

                                <button @click="edit(chapter)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>编辑
                                </button>

                                <button @click="del(chapter.id)" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>删除
                                </button>

                                <button class="btn btn-xs btn-warning" @click="toSection(chapter,course)">
                                    <i class="ace-icon fa fa-flag bigger-120"></i>小节
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
        <!-- Button trigger modal -->

        <!-- Modal -->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">添加课程小节</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" v-model="chapter.name" class="form-control" id="inputEmail3" placeholder="名称" name="chapterName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">课程ID</label>
                                <div class="col-sm-10">
                                     <!--<input type="text" v-model="chapter.courseId" class="form-control" id="inputEmail4" placeholder="课程ID" name="courseId">-->
                                    <p class="form-control-static">{{course.id}}  ({{course.name}})</p>
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
    //导入pagination组件
    import pagination from '../../components/pagination'
    export default {
        //声明子组件
        components:{
            pagination
        },
        name:"chapter",
        data:function () {
            return{
                chapters:[],
                chapter:{},
                course:{}
            }
        },
        mounted() {
            //调用父组件的方法
            //this.$parent.activeSlidebar("business-chapter-sidebar")
            let _this = this

            //在缓存中取出数据，并清空缓存
            let course = sessionStorage.getItem('course')||{}//防止直接访问chapter时造成错误
            //如果是空则跳回到course.vue页面中
            console.log(course)
            let v = JSON.parse(course)
            if(Tools.isEmpty(v)){
                _this.$router.push("/welcome")
            }
            _this.course = v

            //清空缓存
            //sessionStorage.clear()

            _this.$refs.pagination.size = 5
            _this.list(1)
        },
        methods:{
            //该方法在页面加载后执行
            list(page){
                let _this = this;
                //请求之前先加载等待框
                Loading.show()
                //查询每页之前对页面条数设置axios
                _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/chapter/list',
                    {page :page, size : _this.$refs.pagination.size,courseId:_this.course.id}).then((response)=>{
                    //关闭请求框
                    Loading.hide()

                    console.log("查询结果",response)

                    let resp = response.data;
                    _this.chapters = resp.content.list
                    //render是重新渲染(将数据传递到pagination组件内部)
                    _this.$refs.pagination.render(page,resp.content.total)
                })
            },

            //保存大章数据
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
                        chapterName:{
                            validators:{
                                notEmpty:{
                                    message: "小节名不能为空"
                                }
                            }
                        },

                    }
                })
                let bootstrapValidator1 = $("#form-horizontal").data('bootstrapValidator');
                bootstrapValidator1.validate();

                //将传过来的课程id赋值给chapter中的课程id
                _this.chapter.courseId = _this.course.id

                if(!bootstrapValidator1.isValid()){
                    alert('请完善输入项');
                    return _this.list(1);
                }else {
                    _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/chapter/save',
                        _this.chapter).then((response)=>{
                        Loading.hide()
                        console.log("保存结果",response)

                        let resp = response.data
                        //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                        if(resp.success){
                            $("#form-modal").modal("hide")
                            //刷新列表
                            _this.list(1)
                            //调用自定义的Toast方法
                            Toast.success("操作成功！")
                        }else {
                            //弹出后端的提示框(这里的目的是检验如果没有前端校验，利用后端能否检验成功，这里成功了)
                            Toast.warning(resp.message)
                        }
                    })
                }
            },

            //模态框操作
            add(){
                let _this = this
                _this.chapter = {}
                //$("#form-modal")中的modal是CSS选择器,modal()中的参数(show和)是内置的方法，用于弹出或关闭模态框
                $("#form-modal").modal("show")
            },
            //点击哪一行就将哪一行的数据代入编辑
            edit(chapter){
                let _this = this
                //解决vue双向绑定时不保存也会显示修改后的数据（并没有真正保存到数据库）的问题
                _this.chapter = $.extend({},chapter)//临时放到{}中
                $("#form-modal").modal("show")
            },

            //删除大章数据
            del(id){
                let  _this  = this
                Conform.show("你将无法恢复删除的内容",function () {
                    _this.ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/chapter/delete/'+id).then((response)=>{
                        console.log("删除结果",response)

                        let resp = response.data
                        //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                        if(resp.success){
                            //刷新列表
                            _this.list(1)
                            Toast.success("删除成功！")

                        }
                    })

                })

            },

            //跳转到小节
            toSection(chapter,course){
                let _this = this
                sessionStorage.setItem('chapter',JSON.stringify(chapter))
                sessionStorage.setItem('courseFromChapter',JSON.stringify(course))

                _this.$router.push("/business/section")
            },

            //展示树形结构
            initTree(){
                let setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };

                var zNodes =[
                    { id:1, pId:0, name:"随意勾选 1", open:true},
                    { id:11, pId:1, name:"随意勾选 1-1", open:true},
                    { id:111, pId:11, name:"随意勾选 1-1-1"},
                    { id:112, pId:11, name:"随意勾选 1-1-2"},
                    { id:12, pId:1, name:"随意勾选 1-2", open:true},
                    { id:121, pId:12, name:"随意勾选 1-2-1"},
                    { id:122, pId:12, name:"随意勾选 1-2-2"},
                    { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
                    { id:21, pId:2, name:"随意勾选 2-1"},
                    { id:22, pId:2, name:"随意勾选 2-2", open:true},
                    { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
                    { id:222, pId:22, name:"随意勾选 2-2-2"},
                    { id:23, pId:2, name:"随意勾选 2-3"}
                ];
                $.fn.zTree.init($("#tree"),setting,zNodes)
            }
        }
    }
</script>