<template>
    <div class="pagination" role="group" aria-label="分页">
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===1"
        v-on:click="selectPage(1)">
        </button>

        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===1"
                v-on:click="selectPage(page-1)">
            上一页
        </button>

        <button v-for="p in pages" v-bind:id="'page-' + p"
                type="button" class="btn btn-default btn-white btn-round"
                v-bind:class="{'btn-primary active':page==p}"
                v-on:click="selectPage(p)">
            {{p}}
        </button>

        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===pageTotal"
                v-on:click="selectPage(page+1)">
            下一页
        </button>

        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===pageTotal"
                v-on:click="selectPage(pageTotal)">
            {{pageTotal||1}}
        </button>

        <span class="m--padding-10">
            每页
            <select v-model="size">
                <option value="1">1</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            条，共【{{total}}】条
        </span>
    </div>
</template>

<script>
    export default {
        name: "pagination",
        props:{
            //props的作用是定义父组件可以访问到的数据或函数
            list:{
                type:Function,
                default:null
            },
            itemCount:Number //定义显示的页码数，比如可见的页码有5页
        },
        data:function () {
            return{
                total:0,//总行数
                size:10,//每页的数据条数
                page:0,//当前页码
                pageTotal:0,//总页数
                pages:[],//显示页码数组
            }
        },
        methods:{
            //渲染分页组件
            render(page,total){
                let _this = this
                _this.page = page
                _this.total = total
                _this.pageTotal = Math.ceil(total/_this.size)
                _this.pages = _this.getPageItems(_this.pageTotal,page,_this.itemCount||5)//itemCount没有设置时就显示5
            },

            //查询某一页
            selectPage(page){
                let _this = this
                if(page < 1){
                    page = 1
                }
                if (page > _this.pageTotal){
                    page = _this.pageTotal
                }
                if (_this.page != page){
                    _this.page = page
                    if (_this.list){
                        _this.list(page)
                    }
                }
            },

            //当前在页面上的页码
            getPageItems(total,current,length){
                let items = [];
                if (length >= total){
                    for (let i = 1;i<=total;i++){
                        items.push(i)
                    }
                }
                else {
                    let base = 0;
                    //前移
                    if(current- 0 > Math.floor((length - 1)/2)){
                        //后移
                        base = Math.min(total,current - 0 +Math.ceil((length - 1)/2)) - length;
                    }
                    for (let i =1;i<=length;i++){
                        items.push(base+i)
                    }
                }
                return items;
            }
        }
    }
</script>

<style scoped>
    .pagination{
        vertical-align: middle !important;
        font-size: 16px;
        margin-top: 0;
        margin-bottom: 10px;
    }
    .pagination button{
        margin-right: 5px;
    }
    .btn-primary.active{
        background-color: #2f7bba !important;
        border-color: #27689d !important;
        color: white !important;
        font-weight: 600;
    }
</style>