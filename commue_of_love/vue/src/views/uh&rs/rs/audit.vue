<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      height="630"
      :default-sort="{prop: 'increase', order: 'descending'}"
    >
      <el-table-column prop="id" label="ID" sortable width="100" />
      <el-table-column prop="title" label="标题" />
      <el-table-column
        prop="kind"
        label="类别"
        width="70"
        :filters="[{ text: '物资', value: '物资' }, { text: '捐款', value: '捐款' }, { text: '其他', value: '其他' }]"
        filter-placement="bottom-end"
      />
      <el-table-column prop="uid" label="发布账号" sortable width="130" />
      <!--      <el-table-column prop="detail" label="详细内容" v-if="false"/>-->
      <el-table-column prop="province" label="所在地区" sortable width="140" />
      <!--      <el-table-column prop="city" label="所在市" v-if="false"/>-->
      <!--      <el-table-column prop="county" label="所在县" v-if="false"/>-->
      <!--      <el-table-column prop="address" label="详细地址" v-if="false"/>-->
      <!--      <el-table-column prop="contact" label="联系电话" v-if="false"/>-->
      <!--      <el-table-column prop="note" label="备注" v-if="false"/>-->
      <!--      <el-table-column prop="audited" label="审核状态" v-if="false"/>-->
      <!--      <el-table-column prop="level" label="紧急级别" sortable width="130" />-->
      <el-table-column prop="available_time" label="提供时间" width="120" />
      <el-table-column prop="available" label="剩余" width="60" />
      <el-table-column prop="time" label="发布时间" sortable width="170" />
      <el-table-column label="操作" width="210">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="getMore(scope.$index, scope.row)"
          >查看</el-button>
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
          >通过</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >驳回</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogFormVisible" width="70%">
      <el-form ref="form" :model="form">
        <el-row>
          <el-col :span="2" class="line">ID</el-col>
          <el-col :span="2"><el-input v-model="form.id" disabled="true" /></el-col>
          <el-col :span="3" class="line">发布账号</el-col>
          <el-col :span="4"><el-input v-model="form.uid" disabled="true" /></el-col>
          <el-col :span="2" class="line">类别</el-col>
          <el-col :span="2"><el-input v-model="form.kind" disabled="true" /></el-col>
          <el-col :span="3" class="line">发布时间</el-col>
          <el-col :span="5"><el-input v-model="form.time" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">审核</el-col>
          <el-col :span="2"><el-input v-model="form.audited" disabled="true" /></el-col>
          <el-col :span="3" class="line">是否剩余</el-col>
          <el-col :span="4"><el-input v-model="form.available" disabled="true" /></el-col>
          <el-col :span="2" class="line">提供时间</el-col>
          <el-col :span="10"><el-input v-model="form.available_time" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">标题</el-col>
          <el-col :span="21"><el-input v-model="form.title" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">地区</el-col>
          <el-col :span="4"><el-input v-model="form.province" disabled="true" /></el-col>
          <el-col :span="1" class="line">-</el-col>
          <el-col :span="4"><el-input v-model="form.city" disabled="true" /></el-col>
          <el-col :span="1" class="line">-</el-col>
          <el-col :span="4"><el-input v-model="form.county" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">地址</el-col>
          <el-col :span="21"><el-input v-model="form.address" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">内容</el-col>
          <el-col :span="21"><el-input v-model="form.detail" disabled="true" type="textarea" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">备注</el-col>
          <el-col :span="21"><el-input v-model="form.note" disabled="true" type="textarea" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px; text-align: right; padding-right: 40px">
          <el-button type="primary" @click="onSubmit">通过</el-button>
          <el-button type="warning" @click="onSubmit">驳回</el-button>
          <el-button @click="cancel">关闭</el-button>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

export default {
  data() {
    return {
      tableData: [],
      form: {
        id: '',
        uid: '',
        kind: '',
        time: '',
        audited: '',
        available: '',
        available_time: '',
        title: '',
        province: '',
        city: '',
        county: '',
        address: '',
        detail: '',
        note: ''
      },
      dialogFormVisible: false
    }
  },
  created() {
    this.$axios('/rs/findAll').then(res => {
      this.tableData = res.data
      console.log(res.data)
    }).catch(function(error) {
      console.log(error)
    })
  },
  methods: {
    getMore(index, row) {
      // this.$message(row['title'])
      console.log(index, row)
      this.dialogFormVisible = true
      this.form.id = row['id']
      this.form.uid = row['uid']
      this.form.kind = row['kind']
      this.form.time = row['time']
      this.form.audited = row['audited']
      this.form.available_time = row['available_time']
      this.form.available = row['available']
      this.form.title = row['title']
      this.form.province = row['province']
      this.form.city = row['city']
      this.form.county = row['county']
      this.form.address = row['address']
      this.form.detail = row['detail']
      this.form.note = row['note']
    },
    cancel() {
      this.dialogFormVisible = false
    }
  }
}

</script>

<style scoped>
.line{
  margin: 0 auto;
  padding-top: 10px;
  text-align: center;
  font-weight: bold;
}
</style>
