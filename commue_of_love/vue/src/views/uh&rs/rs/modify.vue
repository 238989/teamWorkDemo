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
      <el-table-column prop="uid" label="求助账号" sortable width="130" />
      <el-table-column prop="province" label="所在地区" sortable width="140" />
      <el-table-column prop="available_time" label="提供时间" width="120" />
      <el-table-column prop="available" label="剩余" width="60" />
      <el-table-column prop="time" label="发布时间" sortable width="170" />
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="modify(scope.$index, scope.row)"
          >修改</el-button>
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
          <el-col :span="2">
            <el-select v-model="form.kind" placeholder="请选择">
              <el-option
                v-for="item in kindOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
          <el-col :span="3" class="line">发布时间</el-col>
          <el-col :span="5"><el-input v-model="form.time" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">审核</el-col>
          <el-col :span="2"><el-input v-model="form.audited" disabled="true" /></el-col>
          <el-col :span="3" class="line">是否剩余</el-col>
          <el-col :span="4">
            <el-select v-model="form.available" placeholder="请选择">
              <el-option
                v-for="item in availableOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
          <el-col :span="2" class="line">提供时间</el-col>
          <el-col :span="10"><el-input v-model="form.available_time" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">标题</el-col>
          <el-col :span="21"><el-input v-model="form.title" /></el-col>
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
            />
          </el-col>
          <el-col :span="1" class="line">-</el-col>
          <el-col :span="12"><el-input v-model="form.address" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">内容</el-col>
          <el-col :span="21"><el-input v-model="form.detail" type="textarea" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">备注</el-col>
          <el-col :span="21"><el-input v-model="form.note" type="textarea" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px; text-align: right; padding-right: 40px">
          <el-button type="primary" @click="submit">保存并关闭</el-button>
          <el-button @click="cancel">不保存，直接关闭</el-button>
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
        available_time: '',
        title: '',
        province: '',
        city: '',
        county: '',
        address: '',
        detail: '',
        note: ''
      },
      dialogFormVisible: false,
      // 地区选项
      options: regionData,
      selectedOptions: [],
      availableOptions: [
        {
          value: '是',
          label: '是'
        },
        {
          value: '否',
          label: '否'
        }
      ],
      kindOptions: [
        {
          value: '物资',
          label: '物资'
        },
        {
          value: '捐款',
          label: '捐款'
        },
        {
          value: '其他',
          label: '其他'
        }
      ]
    }
  },
  mounted: function() {
    this.loadRS()
  },
  methods: {
    loadRS() {
      const _this = this
      this.$axios.get('/rs/all').then(resp => {
        if (resp && resp.status === 200) {
          _this.tableData = resp.data
        }
      })
    },
    modify(index, row) {
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
      this.selectedOptions = TextToCode[row['province']][row['city']][row['county']].code
      this.form.address = row['address']
      this.form.detail = row['detail']
      this.form.note = row['note']
    },
    submit() {
      const pcd = this.$refs['regionCat'].getCheckedNodes()[0].pathLabels
      console.log('pcd=' + pcd)
      console.log('province=' + pcd[0])
      console.log('city=' + pcd[1])
      console.log('county=' + pcd[2])
      //  把数据传回后端
      const _this = this
      this.$confirm('是否确认【修改】该内容？', '提示', {
        confirmButtonText: '确认修改',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        _this.$axios.post('/rs/modify', {
          id: _this.form.id,
          uid: _this.form.uid,
          kind: _this.form.kind,
          time: _this.form.time,
          audited: _this.form.audited,
          available: _this.form.available,
          available_time: _this.form.available_time,
          title: _this.form.title,
          province: pcd[0],
          city: pcd[1],
          county: pcd[2],
          address: _this.form.address,
          detail: _this.form.detail,
          note: _this.form.note
        }).then(resp => {
          if (resp && resp.status === 200) {
            _this.$message({ type: 'success', message: '该内容已成功【修改】!' })
            _this.dialogFormVisible = false
            _this.loadRS()
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
