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

        <div class="row">
            <div class="col-md-4" v-for="course in courses">
                <div class="thumbnail search-thumbnail">
                    <!--如果没有上传封面则显示默认封面-->
                    <img v-show="!course.image" class="media-object" src="/static/images/course.jpg"/>
                    <img v-show="course.image" class="media-object" v-bind:src="course.image"/>
                    <div class="caption">
                        <div class="clearfix">
                            <span class="pull-right label label-grey info-label">{{course.level}}</span>
                            <span class="pull-right label label-grey info-label">{{course.charge}}</span>
                            <span class="pull-right label label-grey info-label">{{course.status}}</span>
                        </div>

                        <h3 class="search-title">
                            <a href="#" class="blue">{{course.name}}</a>
                        </h3>
                        <h3 class="search-title">
                            <a href="#" class="red">惊爆价：{{course.price}}￥</a>
                        </h3>
                        <p>{{course.summary}}</p>
                        <td class="hidden-480">
                            <span class="label label-success arrowed-in arrowed-in-right">序号：{{course.sort}}</span>&emsp;
                            <span class="label label-success arrowed-in arrowed-in-right">id：{{course.id}}</span>&emsp;
                            <span class="label label-success arrowed-in arrowed-in-right">时长：{{course.time|formatSecond}}</span>
                            <span class="label label-success arrowed-in arrowed-in-right">已报名{{course.enroll}}人</span>
                        </td>
                        <br>
                        <td class="hidden-480">
                            <span class="label label-info arrowed-in arrowed-in-right">创建时间：{{course.createdAt}}</span>
                        </td>

                        <td class="hidden-480">
                            <span class="label label-info arrowed-in arrowed-in-right">修改时间：{{course.updatedAt}}</span>
                        </td>
                        <br>
                        <p>
                            <button @click="toChapter(course)" class="btn btn-xs btn-warning">
                                <i class="ace-icon fa fa-flag bigger-120"></i>查看章节
                            </button>

                            <button @click="edit(course)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>编辑
                            </button>

                            <button @click="del(course.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>删除
                            </button>
                        </p>
                    </div>
                </div>
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
                                <label class="col-sm-2 control-label">添加分类</label>
                                <div class="col-sm-12">
                                    <ul id="tree" class="ztree"></ul>
                                </div>
                            </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">课程名</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.name" class="form-control" placeholder="课程名" name="courseName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">简介</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.summary" class="form-control" placeholder="简介" name="courseSummary">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">时长（秒）</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.time" class="form-control" placeholder="时长（秒）" name="courseTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">价格</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.price" class="form-control" placeholder="价格" name="coursePrice">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">封面</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.image" class="form-control" placeholder="封面" name="courseImage">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">级别</label>
                                    <div class="col-sm-10">
<!--                                        <input type="text" v-model="course.level" class="form-control" placeholder="级别" name="courseLevel">-->
                                        <select v-model="course.level" class="form-control">
                                            <option v-for="level in levels">{{level.name}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否收费</label>
                                    <div class="col-sm-10">
<!--                                        <input type="text" v-model="course.charge" class="form-control" placeholder="是否收费" name="courseCharge">-->
                                        <select v-model="course.charge" class="form-control">
                                            <option v-for="charge in charges">{{charge.name}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">课程状态</label>
                                    <div class="col-sm-10">
<!--                                        <input type="text" v-model="course.status" class="form-control" placeholder="课程状态" name="courseStatus">-->
                                        <select v-model="course.status" class="form-control">
                                            <option v-for="sta in status">{{sta.name}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">报名人数</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.enroll" class="form-control" placeholder="报名人数" name="courseEnroll">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">顺序</label>
                                    <div class="col-sm-10">
                                        <input type="text" v-model="course.sort" class="form-control" placeholder="顺序" name="courseSort">
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
        name:"course",
        data:function () {
            return{
                courses:[],
                course:{},
                //级别
                levels:[{name: '初级'},
                    {name:'中级'},
                    {name:'高级'}],
                //收费
                charges: [{name:'收费'},
                    {name:'免费'},
                    {name:'章节试看'}],
                //状态
                status:[{name:'已完成'},
                    {name:'未完成'}],
                categorys:[],
                tree:[],
            }
        },
        mounted() {
            //调用父组件的方法
            //this.$parent.activeSlidebar("business-course-sidebar")
            let _this = this
            _this.$refs.pagination.size = 3
            _this.list(1)

            //初始化树形分类
            _this.initTree()
            _this.allCategory()
        },
        methods:{
            //该方法在页面加载后执行
            list(page){
                let _this = this;
                //请求之前先加载等待框
                Loading.show()
                //查询每页之前对页面条数设置axios
                _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/list',
                    {page :page, size : _this.$refs.pagination.size}).then((response)=>{      //关闭请求框
                    Loading.hide()

                    console.log("查询结果",response)

                    let resp = response.data;
                    _this.courses = resp.content.list
                    //render是重新渲染(将数据传递到pagination组件内部)
                    _this.$refs.pagination.render(page,resp.content.total)
                })
            },

            //点击实现跳转到课程中的大章,用html5中的localStorage缓存
            toChapter(course){
                let _this = this

                //存入缓存，要在chapter中将缓存中的数据取出来（mounted中操作）
                sessionStorage.setItem("course",JSON.stringify(course))
                _this.$router.push("/business/chapter")
            },
            //保存数据
            save(){
                let  _this  = this
                //拿到tree中的数据
                let tree = _this.tree.getCheckedNodes()
                if(Tools.isEmpty(tree)){
                    Toast.warning("请先选分类")
                    return
                }
                //console.log(tree)
                //将数据存到course中，然后一起传递到后台，注意与dto中的命名规范保持一致
                _this.course.tree = tree

                    if (Tools.isEmpty(_this.course)){
                    Toast.warning("课程为空")
                }
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
                        courseName:{
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
                    return _this.list(1);
                }else {

                    _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/save',
                        _this.course).then((response)=>{
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
                _this.course = {}
                //添加的时候树节点不选中
                _this.tree.checkAllNodes(false)
                //$("#form-modal")中的modal是CSS选择器,modal()中的参数(show和)是内置的方法，用于弹出或关闭模态框
                $("#form-modal").modal("show")
            },
            //点击哪一行就将哪一行的数据代入编辑
            edit(course){
                let _this = this
                //解决vue双向绑定时不保存也会显示修改后的数据（并没有真正保存到数据库）的问题
                _this.course = $.extend({},course)//临时放到{}中
                //编辑的时候选中节点
                let courseId = _this.course.id
                _this.findCategory(courseId)
                console.log("课程id是"+courseId)
                $("#form-modal").modal("show")
            },

            //删除数据
            del(id){
                let  _this  = this
                Conform.show("你将无法恢复删除的内容",function () {
                    _this.ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/course/delete/'+id).then((response)=>{
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

            //显示所有的分类
            allCategory(){
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

                    _this.initTree()
                })

            },
            //初始化树形结构
            initTree() {
                let _this = this
                var setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            //指定相关的数据
                            idKey:"id",
                            pIdKey:"parent",
                            rootPId:"000",
                            enable: true
                        }
                    }
                };

                let zNodes = _this.categorys
                //将选中的数据存入tree，然后在保存数据的时候取出来
                _this.tree =$.fn.zTree.init($("#tree"),setting,zNodes)
            },
            findCategory(courseId){
                let _this= this
                Loading.show()
                _this.ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/findCategory/'+courseId).then((response)=>{
                    Loading.hide()
                    console.log("分类结构果：",response)
                    let resp = response.data
                    let categorys = resp.content

                    //勾选查询到的分类
                    _this.tree.checkAllNodes(false)
                    for(let i = 0;i<categorys.length;i++){
                        let node = _this.tree.getNodeByParam("id",categorys[i].categoryId)
                        _this.tree.checkNode(node,true)
                    }
                })
            }
        }
    }
</script>