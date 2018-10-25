<template>
  <div id="admin-admin">
    <!--  按钮 -->
    <el-button v-if="this.common.isHaveButton('ADMIN_ADMIN_ADD',$store.state.rules)"
               type="success" icon="el-icon-plus" size="small" @click="adminAdd">新增</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_ADMIN_UPDATE',$store.state.rules)"
               type="warning" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="adminUpdate">修改</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_ADMIN_DELETE',$store.state.rules)"
               type="danger" icon="el-icon-delete" size="small" :disabled="buttonDisabled" @click="adminDelete">删除</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_ADMIN_GROUP',$store.state.rules)"
               type="primary" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="adminGroup">分配角色</el-button><br><br>

    <!-- 列表 -->
    <el-table
      v-loading="loading"
      :data="item"
      ref="admininfoList"
      size="mini"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column prop="adminId" label="管理员Id"></el-table-column>
      <el-table-column prop="adminName" label="账号" ></el-table-column>
      <el-table-column prop="groupId" label="角色" :formatter="getGroupFormat"></el-table-column>
      <el-table-column prop="adminCreateTime" label="创建时间" :formatter="this.common.elTableDateFormat"></el-table-column>
      <el-table-column prop="adminLastLoginTime" label="最新登录时间" :formatter="this.common.elTableDateFormat"></el-table-column>
      <el-table-column prop="adminDesc" label="描述" ></el-table-column>
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
    <el-dialog :title="admininfoFormTitle" :visible.sync="admininfoDisplay" width="30%" center>
      <el-form :label-position="labelPosition" label-width="100px" inline :model="admininfoForm" ref="admininfoForm" >
        <el-form-item label="账号" prop="adminName" :rules="[{ required: true, trigger: 'change', message: '账号不能为空'}]">
          <el-input v-model="admininfoForm.adminName" placeholder="请输入账号" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :rules="[{ validator: validatePass, trigger: 'blur' }]">
          <el-input type="password" v-model="password" placeholder="请输入密码" clearable></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password1" :rules="[{ validator: validatePass2, trigger: 'blur' }]">
          <el-input type="password" v-model="password1" placeholder="请输入确认密码" clearable></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="adminDesc" :rules="[{ required: true, trigger: 'change', message: '描述不能为空'}]">
          <el-input type="textarea" v-model="admininfoForm.adminDesc" placeholder="请输入描述"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="admininfoDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('admininfoForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 分配角色弹窗 -->
    <el-dialog :title="admininfoGroupFormTitle" :visible.sync="admininfoGroupDisplay" width="35%" center>
      <div style="text-align: center">
        <el-transfer
          style="text-align: left; display: inline-block"
          v-model="selectGroups"
          filterable
          :titles="['未选中角色', '已选中角色']"
          :button-texts="['到未选中', '到已选中']"
          :data="groupList">
        </el-transfer>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="admininfoGroupDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitGroupForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import { MLE_GET_OK, MLE_POST_OK, MLE_DELETE_OK, MLE_PUT_OK, MLE_PATCH_OK } from '~/assets/constsUtil.js'
  export default{
    name: 'admin-admin',
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
          size: 20
        },
        admininfoForm: {
          adminId: "",
          adminName: "",
          adminPwd: "",
          adminPwd1: "",
          adminDesc: "",
          groupId: ""
        },
        password: "",   //密码
        password1: "",  //确认密码
        admininfoDisplay: false,  //管理员弹窗开关
        admininfoFormTitle: "",   //管理员弹窗标题
        admininfoGroupDisplay: false,   //管理员分配角色弹窗开关
        admininfoGroupFormTitle: "",    //管理员分配角色弹窗标题
        groupList: [],    //全部角色组列表
        selectGroups: []  //管理员所选中的角色组
      }
    },
    mounted() {
      let self = this
      self.getList()
      self.$store.commit('setRules', sessionStorage.MANAGE_RULES)
      //获取所有角色组
      self.getGroupAll()
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
        this.$axios.$get('/manage/admininfo',{
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
        self.buttonDisabled = true
        self.common.resetForm("admininfoForm", self)
        self.common.updateForm(self.admininfoForm, currentRow)
        if (currentRow) {
          self.buttonDisabled = false
          //封装密码
          self.password = currentRow.adminPwd
          self.password1 = currentRow.adminPwd
          //封装管理员选中的角色组
          let groupIdStr = currentRow.groupId
          let groupArray = new Array()
          if(groupIdStr){
            let groupIds = groupIdStr.split(',')
            let count = 0
            groupIds.forEach((groupId) => {
              groupArray[count] = parseInt(groupId)
              count++
            })
          }
          self.selectGroups = groupArray;
        }
      },
      adminDelete() {
        let self = this
        self.loading = true
        if (self.admininfoForm && self.admininfoForm.adminId) {
          let self = this
          self.$confirm('此操作将删除该管理员用户!', '确认关闭?', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'error',
            center: true
          }).then(() => {
            this.$axios.$delete("/manage/admininfo/"+self.admininfoForm.adminId, {
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
      adminAdd() {
        let self = this
        self.$refs.admininfoList.setCurrentRow();
        self.admininfoFormTitle = "添加管理员"
        self.admininfoDisplay = true
        self.password = ""    //重置密码
        self.password1 = ""   //重置确认密码
      },
      adminUpdate() {
        let self = this
        self.admininfoFormTitle = "修改管理员"
        self.admininfoDisplay = true
      },
      //分配角色
      adminGroup(){
        let self = this
        self.admininfoGroupDisplay = true
        self.admininfoGroupFormTitle = "分配角色组"
      },
      //自定义验证密码
      validatePass(rule, value, callback){
        let self = this
        let password = self.password
        if (!password) {
          callback(new Error('请输入密码!'));
        } else if (password.length < 6) {
          callback(new Error('长度至少6位!'));
        } else {
          callback();
        }
      },
      //自定义验证确认密码
      validatePass2(rule, value, callback) {
        let self = this
        let password1 = self.password1
        if (!password1) {
          callback(new Error('请输入确认密码'));
        } else if (password1 !== self.password) {
          callback(new Error('两次密码不一致!'));
        } else {
          callback();
        }
      },
      //格式化角色组（根据角色ID获取角色名称）
      getGroupFormat(row, column){
        let self = this
        let groupName = ''
        let groupIdStr = row.groupId
        if(groupIdStr){
          let groupIds = groupIdStr.split(',');
          let tabs = self.groupList
          tabs.forEach((tab) => {
            groupIds.forEach((groupId) => {
              if (tab.key == groupId) {
                groupName = groupName + tab.label + ','
              }
            })
          })
        }
        return groupName ? groupName.substring(0, groupName.length - 1) : ''
      },
      //管理员提交
      submitForm(formName) {
        let self = this
        self.loading = true
        self.admininfoForm.adminPwd = self.$md5(self.password)
        self.admininfoForm.adminPwd1 = self.$md5(self.password1)
        if (self.admininfoFormTitle === "添加管理员") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$post("/manage/admininfo/", self.admininfoForm, {
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
              self.admininfoDisplay = false
            }
          });
        } else if (self.admininfoFormTitle === "修改管理员") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$patch("/manage/admininfo/", self.admininfoForm, {
                headers: {
                  'Content-Type': 'application/json'
                }
              })
                .then(function (response) {
                  if( MLE_PATCH_OK ==  response.status && true == response.data) {
                    self.common.showSuccessMessage('修改成功！')
                    self.getList()
                  } else {
                    self.common.showErrorMessage('修改失败！')
                  }
                })
              self.admininfoDisplay = false
            }
          });
        }
        self.loading = false
      },
      //全部角色组
      getGroupAll(){
        let self = this
        self.$axios.$get('/manage/group/getAll')
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              let groupList = res.item
              groupList.forEach((group) => {
                self.groupList.push({
                  key: group.groupId,
                  label: group.groupName
                })
            })
          }
        })
      },
      //分配角色提交
      submitGroupForm(){
        let self = this
        self.loading = true
        self.admininfoForm.groupId = self.selectGroups.join()
        this.$axios.$patch("/manage/admininfo/", self.admininfoForm, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(function (response) {
            if( MLE_POST_OK == response.status && true == response.data) {
              self.common.showSuccessMessage('分配成功！')
              self.getList()
            } else {
              self.common.showErrorMessage('分配失败！')
            }
          })
        self.admininfoGroupDisplay = false
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
