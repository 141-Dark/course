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
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button @click="del(chapter.id)" class="btn btn-xs btn-danger">
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
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" v-model="chapter.name" class="form-control" id="inputEmail3" placeholder="名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail4" class="col-sm-2 control-label">课程ID</label>
                                <div class="col-sm-10">
                                    <input type="text" v-model="chapter.courseId" class="form-control" id="inputEmail4" placeholder="课程ID">
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
                chapter:{}
            }
        },
        mounted() {
            //调用父组件的方法
            //this.$parent.activeSlidebar("business-chapter-sidebar")
            let _this = this
            _this.$refs.pagination.size = 5
            _this.list(1)
        },
        methods:{
            //该方法在页面加载后执行
            list(page){
                let _this = this;

                //查询每页之前对页面条数设置
                _this.ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',
                    {page :page, size : _this.$refs.pagination.size}).then((response)=>{
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
                _this.ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',
                    _this.chapter).then((response)=>{
                    console.log("保存结果",response)

                    let resp = response.data
                    //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                    if(resp.success){
                        $("#form-modal").modal("hide")
                        //刷新列表
                        _this.list(1)
                        //调用自定义的toast方法
                        toast.success("操作成功！")
                    }
                })
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
                //加入弹出框
                Swal.fire({
                    title: '你确定删除吗?',
                    text: "你将无法恢复该内容",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '确认!'
                }).then((result) => {
                    if (result.value) {
                        _this.ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/'+id).then((response)=>{
                            console.log("删除结果",response)

                            let resp = response.data
                            //判断保存是否成功(成功则关闭模态框并从新刷新列表)
                            if(resp.success){
                                //刷新列表
                                _this.list(1)
                                toast.success("删除成功！")

                            }
                        })

                    }
                })

            },
        }
    }
</script>