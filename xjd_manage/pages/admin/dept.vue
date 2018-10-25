<template>
  <div id="admin-action">
    <!-- 查询 -->
    <el-form inline  class="demo-form-inline" size="small">
      <el-form-item label="名称">
        <el-input v-model="searchParam.deptName" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮 -->
    <el-button v-if="this.common.isHaveButton('ADMIN_DEPT_ADD',$store.state.rules)"
               type="success" icon="el-icon-plus" size="small" @click="deptAdd">新增</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_DEPT_UPDATE',$store.state.rules)"
               type="warning" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="deptUpdate">修改</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_DEPT_DELETE',$store.state.rules)"
               type="danger" icon="el-icon-delete" size="small" :disabled="buttonDisabled" @click="deptDelete">删除</el-button>
    <br>
    <br>

    <!-- 列表 -->
    <el-table
      v-loading="loading"
      :data="item"
      ref="deptList"
      size="mini"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column prop="deptId" label="部门Id"></el-table-column>
      <el-table-column prop="deptName" label="部门名称"></el-table-column>
      <el-table-column prop="deptDesc" label="部门描述"></el-table-column>
      <el-table-column prop="deptParentId" label="上级部门ID"></el-table-column>
      <el-table-column prop="deptCreateTime" label="创建时间" :formatter="this.common.elTableDateFormat"></el-table-column>
      <el-table-column prop="deptUpdateTime" label="修改时间" :formatter="this.common.elTableDateFormat"></el-table-column>
    </el-table>
    <br>
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      @prev-click="currentChange"
      @next-click="currentChange"
      :current-page="searchParam.current"
      :page-sizes="[20, 50, 100, 200]"
      :page-size="20"
      layout="total, sizes, prev, pager, next, jumper"
      :total="count">
    </el-pagination>

    <!-- 添加/修改弹窗 -->
    <el-dialog :title="deptFormTitle" :visible.sync="deptDisplay" width="30%" center>
      <el-form :label-position="labelPosition" label-width="100px" inline :model="deptForm" ref="deptForm" >
        <el-form-item label="名称" prop="deptName" :rules="[{ required: true, trigger: 'change', message: '名称不能为空'}]">
          <el-input v-model="deptForm.deptName" placeholder="请输入名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="上级" prop="deptParentId">
          <el-cascader placeholder="试试搜索：管理部"
                       :options="depts"
                       filterable
                       change-on-select
                       clearable
                       @change="changeParentId"
                     v-model="deptParentIds">
          </el-cascader>
        </el-form-item>
        <el-form-item label="描述" prop="deptDesc" >
          <el-input type="textarea" v-model="deptForm.deptDesc" placeholder="请输入描述" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="actionDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('deptForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import { MLE_GET_OK, MLE_POST_OK, MLE_DELETE_OK, MLE_PUT_OK, MLE_PATCH_OK } from '~/assets/constsUtil.js'
  export default{
    name: 'admin-dept',
    data() {
      return {
        count: 0,
        item: [],
        loading: false,
        buttonDisabled: true,
        chooseId: "",
        labelPosition: "right",
        searchParam: {
          current: 1,
          size: 20,
          deptName: ""
        },
        deptForm: {
          deptId: "",
          deptName: "",
          deptDesc: "",
          deptParentId: 0
        },
        deptDisplay: false,
        deptFormTitle: "",
        depts: [],    //全部部门
        deptParentIds: []   //修改部门的上级集合ID
      }
    },
    mounted() {
      let self = this
      self.getList()
      self.$store.commit('setRules', sessionStorage.MANAGE_RULES)
    },
    methods: {
      search() {
        let self = this
        self.searchParam.current = 1
        self.getList()
      },
      getList() {
        let self = this
        self.loading = true
        this.$axios.$get('/manage/dept',{
          params:self.searchParam
        })
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              self.item = res.item
              self.count = res.count
            }
          })
        self.loading = false
      },
      handleCurrentChange(currentRow) {
        let self = this
        self.buttonDisabled = true;
        self.common.resetForm("deptForm", self)
        self.common.updateForm(self.deptForm, currentRow)
        if (currentRow) {
          self.buttonDisabled = false;
        }else{
          //添加时为父类赋值
          self.deptForm.deptParentId = 0
        }
      },
      deptDelete() {
        let self = this
        self.loading = true
        if (self.deptForm && self.deptForm.deptId) {
          self.$confirm('此操作将删除该部门!', '确认关闭?', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'error',
            center: true
          }).then(() => {
            this.$axios.$delete("/manage/dept/"+self.deptForm.deptId, {
              headers: {
                'Content-Type': 'application/json'
              }
            })
              .then(function (response) {
                if( MLE_DELETE_OK == response.status && true == response.data) {
                  self.common.showSuccessMessage('删除成功！')
                  self.getList()
                } else {
                  self.common.showErrorMessage('删除失败！')
                }
              })
          })
        } else {
          self.common.showErrorMessage("请先选择要删除项！")
        }
        self.loading = false
      },
      deptAdd() {
        let self = this
        self.$refs.deptList.setCurrentRow()
        self.deptFormTitle = "添加部门"
        self.deptDisplay = true
        self.deptParentIds = []   //重置父类
        self.getAll()   //获取全部部门
      },
      deptUpdate() {
        let self = this
        self.deptFormTitle = "修改部门"
        self.deptDisplay = true
        //父类赋值
        let deptId = self.deptForm.deptId
        this.$axios.$get('/manage/dept/getParentId/' + deptId)
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              self.deptParentIds = res.data
            }
          })
        self.getAll()   //获取全部部门
      },
      //全部部门
      getAll(){
        let self = this
        self.$axios.get('/manage/dept/getAll')
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              self.depts = res.data.item
            }
          })
      },
      //父类下拉框改变事件
      changeParentId(value){
        let self = this
        //为父类ID设置下拉框选中的根ID
        self.deptForm.deptParentId = value[value.length - 1]
      },
      //提交
      submitForm(formName) {
        let self = this
        self.loading = true
        if (self.deptFormTitle === "添加部门") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$post("/manage/dept/", self.deptForm, {
                headers: {
                  'Content-Type': 'application/json'
                }
              })
                .then(function (response) {
                  if( MLE_POST_OK == response.status && true == response.data) {
                    self.common.showSuccessMessage('添加成功！')
                    self.getList()
                  } else {
                    self.common.showErrorMessage('添加失败！')
                  }
                })
              self.deptDisplay = false
            }
          });
        } else if (self.deptFormTitle === "修改部门") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$put("/manage/dept/", self.deptForm, {
                headers: {
                  'Content-Type': 'application/json'
                }
              })
                .then(function (response) {
                  if( MLE_PUT_OK ==  response.status && true == response.data) {
                    self.common.showSuccessMessage('修改成功！')
                    self.getList()
                  } else {
                    self.common.showErrorMessage('修改失败！')
                  }
                })
              self.deptDisplay = false
            }
          });
        }
        self.loading = false
      },
      //分页选择每页条数回调函数 (条数)
      sizeChange(size) {
        let self = this
        self.searchParam.size = size
        self.searchParam.current = 1
        self.getList()
      },
      //分页选择页码回调函数 (当前选择页码)
      currentChange(current) {
        let self = this
        self.searchParam.current = current
        self.getList()
      }
    }
  }
</script>
