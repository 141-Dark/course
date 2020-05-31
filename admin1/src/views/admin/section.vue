<template>
    <div>
        <p>
            <button @click="add()" class="btn btn-white btn-default btn-round">
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
                            <th>标题</th>
                            <th>课程id</th>
                            <th>大章id</th>
                            <th>视频</th>
                            <th>时长</th>
                            <th>是否收费</th>
                            <th>顺序</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="section in sections">
                            <td>{{section.id}}</td>
                            <td>{{section.title}}</td>
                            <td>{{section.courseId}}</td>
                            <td>{{section.chapterId}}</td>
                            <td>{{section.video}}</td>
                            <td>{{section.time}}</td>
                            <td>{{section.charge}}</td>
                            <td>{{section.sort}}</td>
                            <td>{{section.createdAt}}</td>
                            <td>{{section.updatedAt}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">

                                <button @click="edit(section)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button @click="del(section.id)" class="btn btn-xs btn-danger">
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
                                    <label for="inputEmail1" class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.title" class="form-control" id="inputEmail1" placeholder="标题" name="sectionTitle">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail2" class="col-sm-2 control-label">课程id</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.courseId" class="form-control" id="inputEmail2" placeholder="课程id" name="courseId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">大章id</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.chapterId" class="form-control" id="inputEmail3" placeholder="大章id" name="chapterId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail4" class="col-sm-2 control-label">视频</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.video" class="form-control" id="inputEmail4" placeholder="视频" name="video">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail6" class="col-sm-2 control-label">时长</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.time" class="form-control" id="inputEmail6" placeholder="时长" name="time">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail7" class="col-sm-2 control-label">是否收费</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.charge" class="form-control" id="inputEmail7" placeholder="是否收费" name="charge">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail8" class="col-sm-2 control-label">顺序</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="section.sort" class="form-control" id="inputEmail8" placeholder="顺序" name="sort">
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
        name:"section",
        data:function () {
            return{
                sections:[],
                section:{}
            }
        },
        mounted() {
            //调用父组件的方法
            //this.$parent.activeSlidebar("business-section-sidebar")
            let _this = this
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
                _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/list',
                    {page :page, size : _this.$refs.pagination.size}).then((response)=>{
                    //关闭请求框
                    Loading.hide()

                    console.log("查询结果",response)

                    let resp = response.data;
                    _this.sections = resp.content.list
                    //render是重新渲染(将数据传递到pagination组件内部)
                    _this.$refs.pagination.render(page,resp.content.total)
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
                        sectionTitle:{
                            validators:{
                                notEmpty:{
                                    message: "小节标题不能为空"
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
                                    max: 13,
                                    message: 'id长度必须在1到8位之间'
                                },
                            }
                        },
                        chapterId:{
                            validators:{
                                notEmpty:{
                                    message: "大章id不能为空"
                                }
                            }
                        },
                    }
                })
                let bootstrapValidator1 = $("#form-horizontal").data('bootstrapValidator');
                bootstrapValidator1.validate();

                if(!bootstrapValidator1.isValid()){
                    alert('请完善输入项');
                    return _this.list(1);
                }else {
                    _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/save',
                        _this.section).then((response)=>{
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
                _this.section = {}
                //$("#form-modal")中的modal是CSS选择器,modal()中的参数(show和)是内置的方法，用于弹出或关闭模态框
                $("#form-modal").modal("show")
            },
            //点击哪一行就将哪一行的数据代入编辑
            edit(section){
                let _this = this
                //解决vue双向绑定时不保存也会显示修改后的数据（并没有真正保存到数据库）的问题
                _this.section = $.extend({},section)//临时放到{}中
                $("#form-modal").modal("show")
            },

            //删除数据
            del(id){
                let  _this  = this
                Conform.show("你将无法恢复删除的内容",function () {
                    _this.ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/section/delete/'+id).then((response)=>{
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
                //加入弹出框
            //     Swal.fire({
            //         title: '你确定删除吗?',
            //         text: "",
            //         icon: 'warning',
            //         showCancelButton: true,
            //         confirmButtonColor: '#3085d6',
            //         cancelButtonColor: '#d33',
            //         confirmButtonText: '确认!'
            //     }).then((result) => {
            //         if (result.value) {
            //
            //         }
            //     })
            //
            },
        }
    }
</script>