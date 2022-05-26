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
        :filters="[{ text: '物资', value: '物资' }, { text: '捐款', value: '捐款' }, { text: '其他', value: '其他' }]"
        filter-placement="bottom-end"
      />
      <el-table-column prop="uid" label="求助账号" sortable width="130" />
      <!--      <el-table-column prop="detail" label="详细内容" v-if="false"/>-->
      <el-table-column prop="province" label="所在地区" sortable width="140" />
      <!--      <el-table-column prop="city" label="所在市" v-if="false"/>-->
      <!--      <el-table-column prop="county" label="所在县" v-if="false"/>-->
      <!--      <el-table-column prop="address" label="详细地址" v-if="false"/>-->
      <!--      <el-table-column prop="completed" label="是否解决" v-if="false"/>-->
      <!--      <el-table-column prop="contact" label="联系电话" v-if="false"/>-->
      <!--      <el-table-column prop="note" label="备注" v-if="false"/>-->
      <!--      <el-table-column prop="audited" label="审核状态" v-if="false"/>-->
      <!--      <el-table-column prop="level" label="紧急级别" sortable width="130" />-->
      <el-table-column
        prop="level"
        label="紧急级别"
        width="120"
        :filters="[{ text: '紧急', value: '紧急' }, { text: '中等', value: '中等' }, { text: '一般', value: '一般' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="tagType(scope.row.level)"
            close-transition
          >{{ scope.row.level }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="发布时间" sortable width="170" />
      <el-table-column prop="completed" label="是否解决" sortable width="130" />
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
            <!--            <el-input v-model="form.kind" />-->
          </el-col>
          <el-col :span="3" class="line">发布时间</el-col>
          <el-col :span="5"><el-input v-model="form.time" disabled="true" /></el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="2" class="line">审核</el-col>
          <el-col :span="2"><el-input v-model="form.audited" disabled="true" /></el-col>
          <el-col :span="3" class="line">紧急级别</el-col>
          <el-col :span="4">
            <el-select v-model="form.level" placeholder="请选择">
              <el-option
                v-for="item in levelOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <!--            <el-input v-model="form.level" />-->
          </el-col>
          <el-col :span="2" class="line">解决</el-col>
          <el-col :span="3">
            <el-select v-model="form.completed" placeholder="请选择">
              <el-option
                v-for="item in completedOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          <!--            <el-input v-model="form.completed" />-->
          </el-col>
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
        <!--          <el-col :span="4"><el-input v-model="form.province" /></el-col>-->
        <!--          <el-col :span="4"><el-input v-model="form.city" /></el-col>-->
        <!--          <el-col :span="1" class="line">-</el-col>-->
        <!--          <el-col :span="4"><el-input v-model="form.county" /></el-col>-->
        <!--        </el-row>-->
        <!--        <el-row style="margin-top: 20px">-->
        <!--          <el-col :span="2" class="line">地址</el-col>-->
        <!--          <el-col :span="14">-->
        <!--            <v-distpicker v-show="true" :province="form.province" :city="form.city" :area="form.county" @selected="pcdSelect"></v-distpicker>-->
        <!--          </el-col>-->
        <!--        </el-row>-->
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
import { regionData } from 'element-china-area-data/dist/app'
import {TextToCode} from "element-china-area-data"; // 地址级联选择器

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
        level: '',
        completed: '',
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
      // 紧急级别选项
      levelOptions: [
        {
          value: '紧急',
          label: '紧急'
        },
        {
          value: '中等',
          label: '中等'
        },
        {
          value: '一般',
          label: '一般'
        }
      ],
      completedOptions: [
        {
          value: '未解决',
          label: '未解决'
        },
        {
          value: '已解决',
          label: '已解决'
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
  created() {
    this.$axios('/uh/findAll').then(res => {
      this.tableData = res.data
      console.log(res.data)
    }).catch(function(error) {
      console.log(error)
    })
  },
  methods: {
    tagType(lvl) {
      if (lvl === '紧急') {
        return 'danger'
      } else if (lvl === '中等') {
        return 'warning'
      } else {
        return 'success'
      }
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
      this.form.level = row['level']
      this.form.completed = row['completed']
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
      this.$message('修改成功！')
      this.dialogFormVisible = false
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
