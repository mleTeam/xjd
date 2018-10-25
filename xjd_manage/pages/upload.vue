<template>
    <div id="testUpload">
      <el-upload
        class="upload-demo"
        action="http://cs.milibanking.com:9988/fastdfs/upload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        :multiple="false"
        :limit="3"
        :show-file-list="false"
        :on-exceed="handleExceed"
        :http-request="upload">
        <el-button size="small" type="primary">选取文件</el-button>
      </el-upload>
    </div>
</template>

<script>
export default {
  layout: 'empty',
  data() {
    return {
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    upload(item) {
      let formData = new FormData();
      formData.append('file', item.file)
      this.$axios.$post('/api/fastdfs/upload',formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(function (response) {
          console.log('成功回调:')
          console.log(response)
        })
        .catch(function (error) {
          console.log('失败回调:')
          console.log(error)
        });
    }
  }
}
</script>
