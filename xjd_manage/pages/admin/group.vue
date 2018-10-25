<template>
  <div id="admin-group">
    <!--  按钮 -->
    <el-button v-if="this.common.isHaveButton('ADMIN_GROUP_ADD',$store.state.rules)"
               type="success" icon="el-icon-plus" size="small" @click="groupAdd">新增</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_GROUP_UPDATE',$store.state.rules)"
               type="warning" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="groupUpdate">修改</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_GROUP_DELETE',$store.state.rules)"
               type="danger" icon="el-icon-delete" size="small" :disabled="buttonDisabled" @click="groupDelete">删除</el-button>
    <el-button v-if="this.common.isHaveButton('ADMIN_GROUP_ACTION',$store.state.rules)"
               type="primary" icon="el-icon-edit" size="small" :disabled="buttonDisabled" @click="groupAction">分配权限</el-button><br><br>

    <!-- 列表 -->
    <el-table
      v-loading="loading"
      :data="item"
      ref="groupList"
      size="mini"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column prop="groupId" label="角色Id"></el-table-column>
      <el-table-column prop="groupName" label="角色名称" ></el-table-column>
      <el-table-column prop="groupInfo" label="角色描述"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="this.common.elTableDateFormat"></el-table-column>
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
    <el-dialog :title="groupFormTitle" :visible.sync="groupDisplay" width="30%" center>
      <el-form :label-position="labelPosition" label-width="100px" inline :model="groupForm" ref="groupForm" >
        <el-form-item label="名称" prop="groupName" :rules="[{ required: true, trigger: 'change', message: '名称不能为空'}]">
          <el-input v-model="groupForm.groupName" placeholder="请输入名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="groupInfo" :rules="[{ required: true, trigger: 'change', message: '描述不能为空'}]">
          <el-input v-model="groupForm.groupInfo" placeholder="请输入描述" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="groupDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('groupForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 分配权限弹窗 -->
    <el-dialog :title="groupActionFormTitle" :visible.sync="groupActionDisplay" width="22%" center>
      <el-tree
        ref="groupTree"
        :data="actionProps"
        node-key="value"
        show-checkbox
        highlight-current
        :props="defaultProps">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="groupActionDisplay = false">取 消</el-button>
        <el-button type="primary" @click="submitGroupActionForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import { MLE_GET_OK, MLE_POST_OK, MLE_DELETE_OK, MLE_PUT_OK, MLE_PATCH_OK } from '~/assets/constsUtil.js'
  export default{
    name: 'admin-group',
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
        groupForm: {
          groupId: "",
          groupName: "",
          groupInfo: "",
          groupCheckAction: "",     //选中的权限ID
          groupHalfCheckAction: ""  //半选中的权限ID
        },
        groupDisplay: false,    //角色组弹窗开关
        groupFormTitle: "",     //角色组弹窗标题
        groupActionDisplay: false,  //分配权限弹窗开关
        groupActionFormTitle: "",   //分配权限弹窗标题
        actionProps: [],      //全部权限
        checkActionProps: [],   //角色组选中的权限
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    mounted() {
      let self = this
      self.getList()
      self.$store.commit('setRules', sessionStorage.MANAGE_RULES)
      //获取全部权限
      self.getActionAll()
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
        this.$axios.$get('/manage/group')
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
        self.common.resetForm("groupForm", self)
        self.common.updateForm(self.groupForm, currentRow)
        //封装角色对应的选中权限
        let actionArray = new Array()
        if (currentRow) {
          self.buttonDisabled = false;
          let groupCheckAction = currentRow.groupCheckAction
          if(groupCheckAction){
            let count = 0
            let groupCheckActions = groupCheckAction.split(',')
            groupCheckActions.forEach((actionId) => {
              actionArray[count] = parseInt(actionId)
              count++
            })
          }
        }
        self.checkActionProps = actionArray
      },
      groupDelete() {
        let self = this
        self.loading = true
        if (self.groupForm && self.groupForm.groupId) {
          let self = this
          self.$confirm('此操作将删除该角色!', '确认关闭?', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'error',
            center: true
          }).then(() => {
            self.$axios.$delete("/manage/group/"+self.groupForm.groupId, {
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
      groupAdd() {
        let self = this
        self.$refs.groupList.setCurrentRow();
        self.groupFormTitle = "添加角色"
        self.groupDisplay = true
      },
      groupUpdate() {
        let self = this
        self.groupFormTitle = "修改角色"
        self.groupDisplay = true
      },
      //分配权限
      groupAction() {
        let self = this
        self.groupActionDisplay = true
        self.groupActionFormTitle = "分配权限"
        //设置权限树（等待弹窗显示出来再设置权限树）
        setTimeout(() => {
          self.$refs.groupTree.setCheckedKeys(self.checkActionProps);
        }, 0)
      },
      //角色提交
      submitForm(formName) {
        let self = this
        self.loading = true
        self.groupDisplay = false
        if (self.groupFormTitle === "添加角色") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              this.$axios.$post("/manage/group/", self.groupForm, {
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
            }
          });
        } else if (self.groupFormTitle === "修改角色") {
          this.$refs[formName].validate((valid) => {
            if(valid) {
              self.$axios.$patch("/manage/group/", self.groupForm, {
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
            }
          });
        }
        self.loading = false
      },
      //全部权限
      getActionAll() {
        let self = this
        self.$axios.$get('/manage/action/getAll')
          .then((res) =>{
            if(MLE_GET_OK == res.status){
              self.actionProps = res.item
            }
          })
      },
      //分配权限提交
      submitGroupActionForm(){
        let self = this
        self.loading = true
        //设置选中的权限
        self.groupForm.groupCheckAction = self.$refs.groupTree.getCheckedKeys().join()
        //设置半选中的权限
        self.groupForm.groupHalfCheckAction = self.$refs.groupTree.getHalfCheckedKeys().join()
        self.$axios.$patch("/manage/group/", self.groupForm, {
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
        self.loading = false
        self.groupActionDisplay = false
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
