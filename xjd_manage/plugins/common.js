import Vue from 'vue'
import moment from 'moment'
import  { Message }  from 'element-ui'
//日期格式化函数 (行对象row，列对象column，单元格值cellValue，索引index)

var common = {
  install(Vue){
    Vue.prototype.common = {
      updateForm:function (form, row) {
        if (form) {
          for(let name in form){
            form[name] = row ? row[name] : ""
          }
        }
      },
      resetForm:function (name, component) {
        if(component.$refs[name]){
          component.$refs[name].resetFields();
        }
      },
      isHaveButton:function (value,rules){
        let flag = false
        if (rules) {
          let list = rules.split(",")
          if (list) {
            for (let i in list) {
              if (list[i] == value) {
                flag = true
              }
            }
          }
        }
        return flag
      } ,
      elTableDateFormat:function(row, column,cellValue){
        if (cellValue) {
          return moment(cellValue).format("YYYY-MM-DD HH:mm:ss")
        }
        return ""
      },
      showResponseError:function(error) {
        let msg = error.response.message ? error.response.message : '系统异常！'
        Message({
          message: msg,
          type: 'error'
        });
      },
      showErrorMessage:function(msg) {
        Message({
          message: msg,
          type: 'error'
        });
      },
      showSuccessMessage:function(msg) {
        Message({
          message: msg,
          type: 'success'
        });
      }
    };

  }
}

Vue.use(common);
