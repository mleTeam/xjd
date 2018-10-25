<template>
  <div id="admin-action">
    <!-- 查询 -->
    <el-form inline  class="demo-form-inline" size="small">
      <el-form-item label="名称">
        <el-input v-model="searchParam.actionName" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="searchParam.actionUrl" placeholder="地址"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="searchParam.actionIsButton" placeholder="请选择类型">
          <el-option label="全部" value=""></el-option>
          <el-option label="导航" value="1"></el-option>
          <el-option label="按钮" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮 -->
    <el-button v-if="this.common.isHaveButton('ADMIN_ACTION_ADD',$store.state.rules)"
               type="success" icon="el-icon-plus" size="small" @click="actionAdd">新增</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_ACTION_UPDATE',$store.state.rules)"
               type="warning" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="actionUpdate">修改</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_ACTION_DELETE',$store.state.rules)"
               type="danger" icon="el-icon-delete" size="small" :disabled="buttonDisabled" @click="actionDelete">删除</el-button>
    <br>
    <br>

    <!-- 列表 -->
    <el-table
      v-loading="loading"
      :data="item"
      ref="actionList"
      size="mini"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column prop="actionId" label="权限Id" width="80"></el-table-column>
      <el-table-column prop="actionName" label="名称"></el-table-column>
      <el-table-column prop="actionUrl" label="地址"></el-table-column>
      <el-table-column prop="actionViewMode" label="导航栏" :formatter="getActionViewModeDescribe" width="80"></el-table-column>
      <el-table-column prop="actionParentId" label="父类ID" width="80"></el-table-column>
      <el-table-column prop="actionSort" label="排序" width="80"></el-table-column>
      <el-table-column prop="actionRule" label="权限" ></el-table-column>
      <el-table-column
        prop="actionIsButton"
        label="标签"
        width="100"
        :filters="[{ text: '导航', value: '1' }, { text: '按钮', value: '2' }]"
        :filter-method="filterButton"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.actionIsButton == '1' ? 'primary' : 'success'"
            disable-transitions>{{scope.row.actionIsButton == '1' ? '导航' : '按钮'}}</el-tag>
        </template>
      </el-table-column>
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
    <el-dialog :title="actionFormTitle" :visible.sync="actionDisplay" width="30%" center>
      <el-form :label-position="labelPosition" label-width="100px" inline :model="actionForm" ref="actionForm" >
        <el-form-item label="名称" prop="actionName" :rules="[{ required: true, trigger: 'change', message: '名称不能为空'}]">
          <el-input v-model="actionForm.actionName" placeholder="请输入名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="actionUrl" >
          <el-input v-model="actionForm.actionUrl" placeholder="请输入地址" clearable></el-input>
        </el-form-item>
        <el-form-item label="导航栏" prop="actionViewMode" :rules="[{ required: true, trigger: 'change', message: '导航栏选择不能为空'}]">
          <el-radio-group v-model="actionForm.actionViewMode">
            <el-radio :label="1">显示</el-radio>
            <el-radio :label="2">不显示</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="父类" prop="actionParentId">
          <el-cascader placeholder="试试搜索：管理员"
                       :options="actions"
                       filterable
                       change-on-select
                       clearable
                       @change="changeParentId"
                     v-model="actionParentIds">
          </el-cascader>
        </el-form-item>
        <el-form-item label="session" prop="actionIsSession" :rules="[{ required: true, trigger: 'change', message: 'session不能为空'}]">
          <el-radio-group v-model="actionForm.actionIsSession">
            <el-radio :label="1">不需要</el-radio>
            <el-radio :label="2">需要</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="按钮" prop="actionIsButton" :rules="[{ required: true, trigger: 'change', message: '按钮不能为空'}]">
          <el-radio-group v-model="actionForm.actionIsButton">
            <el-radio :label="1">否</el-radio>
            <el-radio :label="2">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序" prop="actionSort" :rules="[{ required: true, trigger: 'change', message: '排序不能为空'}]">
          <el-input v-model="actionForm.actionSort" placeholder="请输入排序" clearable></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="actionRule" >
          <el-input v-model="actionForm.actionRule" placeholder="请输入权限" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="actionDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('actionForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import { MLE_GET_OK, MLE_POST_OK, MLE_DELETE_OK, MLE_PUT_OK, MLE_PATCH_OK } from '~/assets/constsUtil.js'
  export default{
    name: 'admin-action',
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
          actionName: "",
          actionUrl: "",
          actionIsButton: ""
        },
        actionForm: {
          actionId: "",
          actionName: "",
          actionUrl: "",
          actionViewMode: 1,
          actionParentId: 0,
          actionIsSession: 1,
          actionIsButton: 1,
          actionSort: "",
          actionRule: ""
        },
        actionDisplay: false,
        actionFormTitle: "",
        actions: [],    //全部一级二级权限列表
        actionParentIds: []   //修改权限的上级集合ID
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
        this.$axios.$get('/manage/action',{
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
        self.common.resetForm("actionForm", self)
        self.common.updateForm(self.actionForm, currentRow)
        if (currentRow) {
          self.buttonDisabled = false;
        }else{
          //添加时为单选按钮赋值
          self.actionForm.actionViewMode = 1
          self.actionForm.actionIsButton = 1
          self.actionForm.actionIsSession = 1
          //添加时为父类赋值
          self.actionForm.actionParentId = 0
        }
      },
      actionDelete() {
        let self = this
        self.loading = true
        if (self.actionForm && self.actionForm.actionId) {
          self.$confirm('此操作将删除该权限!', '确认关闭?', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'error',
            center: true
          }).then(() => {
            this.$axios.$delete("/manage/action/"+self.actionForm.actionId, {
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
      actionAdd() {
        let self = this
        self.$refs.actionList.setCurrentRow()
        self.actionFormTitle = "添加权限"
        self.actionDisplay = true
        self.actionParentIds = []   //重置父类
        self.getParentAll()   //获取全部一级二级
      },
      actionUpdate() {
        let self = this
        self.actionFormTitle = "修改权限"
        self.actionDisplay = true
        //父类赋值
        let actionParentId = self.actionForm.actionParentId
        if(actionParentId != 0){
          this.$axios.$get('/manage/action/' + actionParentId)
            .then((res) =>{
              if(MLE_GET_OK == res.status){
                let action = res.data
                //判断是否一级
                if(action.actionParentId != 0){
                  //否，封装一级二级
                  self.actionParentIds = [action.actionParentId, action.actionId]
                }else{
                  //是，封装一级
                  self.actionParentIds = [action.actionId]
                }
              }
            })
        }
        self.getParentAll()   //获取全部一级二级
      },
      //格式化左侧导航栏显示状态
      getActionViewModeDescribe(row,column){
        switch(row.actionViewMode) {
          case 1:
            return '显示'
            break
          case 2:
            return '不显示'
            break
        }
      },
      filterButton(value, row){
        return row.actionIsButton == value;
      },
      //全部一级二级权限
      getParentAll(){
        let self = this
        self.$axios.get('/manage/action/getParentAll')
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              self.actions = res.data.item
            }
          })
      },
      //父类下拉框改变事件
      changeParentId(value){
        let self = this
        //为父类ID设置下拉框选中的根ID
        self.actionForm.actionParentId = value[value.length - 1]
      },
      //提交
      submitForm(formName) {
        let self = this
        self.loading = true
        if (!self.actionForm.actionParentId){
          self.actionForm.actionParentId = 0
        }
        if (self.actionFormTitle === "添加权限") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$post("/manage/action/", self.actionForm, {
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
              self.actionDisplay = false
            }
          });
        } else if (self.actionFormTitle === "修改权限") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$put("/manage/action/", self.actionForm, {
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
              self.actionDisplay = false
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
