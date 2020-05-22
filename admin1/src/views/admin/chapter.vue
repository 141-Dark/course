<template>
    <div>
        <p>
            <button @click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新页面
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
                                <button class="btn btn-xs btn-success">
                                    <i class="ace-icon fa fa-check bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-danger">
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
                chapters:[]
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
                    _this.chapters = response.data.list
                    //render是重新渲染(将数据传递到pagination组件内部)
                    _this.$refs.pagination.render(page,response.data.total)
                })
            },

        }
    }
</script>