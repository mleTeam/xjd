<template>
  <el-container>
      <el-header height="60px">
        <span style="color: #fff;">
          现金贷后台管理
        </span>
      </el-header>
    <el-container>
      <el-aside width="240px">
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :unique-opened="isUnique" style="min-height: 805px;">
          <el-submenu :index="String(menu.actionId)" v-for="menu in menuList" :key="menu.actionId">
            <template slot="title">{{ menu.actionName }}</template>
            <el-menu-item @click="goRoute(submenu.actionName,submenu.actionId+'',submenu.actionUrl)" :index="menu.actionId + '-' + submenu.actionId" v-for="submenu in menu.actionList" :key="submenu.actionId">{{ submenu.actionName }}</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
        <el-main>
          <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab"  @tab-click="clickTab" >
            <el-tab-pane
              v-for="item in editableTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
            >
            </el-tab-pane>
          </el-tabs>
          <nuxt/>
        </el-main>
    </el-container>
  </el-container>
</template>
<script type="text/ecmascript-6">
  import { MLE_GET_OK } from '~/assets/constsUtil.js'
  export default {
    data() {
      return {
        isUnique: true,
        menuList: [],
        editableTabsValue: '1',
        editableTabs: []
      }
    },
    mounted() {
      let self = this
      this.$axios.$get('/manage/menus','')
        .then((res) =>{
            self.menuList = res.item
        })
    },
    methods: {
      goRoute(targetTitle,targetId,path) {
        let flag = true
        let self = this
        self.editableTabs.forEach((tab) => {
          if (tab.name === targetId) {
            self.editableTabsValue = targetId
            flag = false
          }
        });
        if(flag){
          self.addTab(targetTitle,targetId,path)
        }
        self.$router.push(path)
      },
      addTab(targetTitle,targetId,path) {
        this.editableTabs.push({
          title: targetTitle,
          name: targetId,
          content: path
        });
        this.editableTabsValue = targetId;
      },
      removeTab(targetName) {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        let path = '/home'
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
                path = nextTab.content
              }
            }
          });
          this.$router.push(path)
        }

        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      },
      clickTab() {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        tabs.forEach((tab, index) => {
          if (tab.name === activeName) {
            this.$router.push(tab.content)
          }
        })
      }
    }
  }
</script>
<style >
  body{
    margin:0
  }
  .el-header{
    line-height: 80px;
    background-color: rgb(64, 158, 255);
  }
  .el-menu{
    height: 100%;
  }
</style>
