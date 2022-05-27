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
        width="120"
        :filters="[{ text: '物资', value: '物资' }, { text: '捐款', value: '捐款' }]"
        filter-placement="bottom-end"
      />
      <el-table-column prop="uid" label="求助账号" sortable width="130" />
      <el-table-column prop="province" label="所在地区" sortable width="140" />
      <el-table-column prop="time" label="发布时间" sortable width="170" />
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="getMore(scope.$index, scope.row)"
          >查看</el-button>
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
          <el-col :span="3" class="line">项目状态</el-col>
          <el-col :span="2"><el-input v-model="form.available" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">标题</el-col>
          <el-col :span="21"><el-input v-model="form.title" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">地址</el-col>
          <el-col :span="8">
            <el-cascader
              ref="regionCat"
              v-model="selectedOptions"
              style="width: 100%;"
              size="large"
              :options="options"
              placeholder="请选择"
              disabled="true"
            />
          </el-col>
          <el-col :span="1" class="line">-</el-col>
          <el-col :span="12"><el-input v-model="form.address" disabled="true" /></el-col>
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
          <el-button type="primary" @click="pass">通过</el-button>
          <el-button type="warning" @click="fail">驳回</el-button>
          <el-button @click="cancel">关闭</el-button>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { regionData, TextToCode } from 'element-china-area-data/dist/app' // 地址级联选择器

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
        title: '',
        province: '',
        city: '',
        county: '',
        address: '',
        detail: '',
        note: ''
      },
      // 地区选项
      options: regionData,
      selectedOptions: [],
      dialogFormVisible: false
    }
  },
  mounted: function() {
    this.loadDN()
  },
  methods: {
    loadDN() {
      const _this = this
      this.$axios.post('/dn/unaudited', {
        id: '',
        uid: '',
        kind: '',
        time: '',
        audited: 'unaudited',
        available: '',
        available_time: '',
        title: '',
        province: '',
        city: '',
        county: '',
        address: '',
        detail: '',
        note: ''
      }).then(resp => {
        if (resp && resp.status === 200) {
          _this.tableData = resp.data
        }
      })
    },
    getMore(index, row) {
      // this.$message(row['title'])
      console.log(index, row)
      this.dialogFormVisible = true
      this.form.id = row['id']
      this.form.uid = row['uid']
      this.form.kind = row['kind']
      this.form.time = row['time']
      this.form.audited = row['audited']
      this.form.available = row['available']
      this.form.title = row['title']
      this.selectedOptions = TextToCode[row['province']][row['city']][row['county']].code
      this.form.address = row['address']
      this.form.detail = row['detail']
      this.form.note = row['note']
    },
    pass() {
      const _this = this
      this.$confirm('是否确认【通过】该内容？', '提示', {
        confirmButtonText: '确认通过',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        _this.$axios.post('/dn/audited', {
          id: _this.form.id,
          uid: '',
          kind: '',
          time: '',
          audited: 'passed',
          available: '',
          available_time: '',
          title: '',
          province: '',
          city: '',
          county: '',
          address: '',
          detail: '',
          note: ''
        }).then(resp => {
          if (resp && resp.status === 200) {
            _this.$message({ type: 'success', message: '该内容已成功【通过】!' })
            _this.dialogFormVisible = false
            _this.loadDN()
          }
        }).catch(() => {
          this.$message({ type: 'info', message: '已取消操作！' })
        })
      })
    },
    fail() {
      const _this = this
      this.$confirm('是否确认【驳回】该内容？', '提示', {
        confirmButtonText: '确认驳回',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        _this.$axios.post('/dn/audited', {
          id: _this.form.id,
          uid: '',
          kind: '',
          time: '',
          audited: 'failed',
          available: '',
          available_time: '',
          title: '',
          province: '',
          city: '',
          county: '',
          address: '',
          detail: '',
          note: ''
        }).then(resp => {
          if (resp && resp.status === 200) {
            _this.$message({ type: 'success', message: '该内容已成功【驳回】!' })
            _this.dialogFormVisible = false
            _this.loadDN()
          }
        }).catch(() => {
          this.$message({ type: 'info', message: '已取消操作！' })
        })
      })
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
