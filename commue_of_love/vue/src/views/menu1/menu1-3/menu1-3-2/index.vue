<template>
  <div class="app-container">
    <el-select v-model="selectedArray" multiple collapse-tags placeholder="请选择" @change="changeSelect" @remove-tag="removeTag">
      <el-option label="全选" value="全选" @click.native="selectAll" />
      <el-option v-for="(item, index) in options" :key="index" :label="item.name" :value="item.name" />
    </el-select>
  </div>

</template>

<script>
export default {
  data() {
    return {
      selectedArray: [],
      options: [
        { name: '一一', label: 'one' },
        { name: '二二', label: 'tow' },
        { name: '三三', label: 'three' },
        { name: '四四', label: 'four' },
        { name: '五五', label: 'five' }
      ]
    }
  },
  methods: {
    selectAll() {
      if (this.selectedArray.length < this.options.length) {
        this.selectedArray = []
        this.options.map((item) => {
          this.selectedArray.push(item.name)
        })
        this.selectedArray.unshift('全选')
      } else {
        this.selectedArray = []
      }
    },
    changeSelect(val) {
      if (!val.includes('全选') && val.length === this.options.length) {
        this.selectedArray.unshift('全选')
      } else if (val.includes('全选') && (val.length - 1) < this.options.length) {
        this.selectedArray = this.selectedArray.filter((item) => {
          return item !== '全选'
        })
      }
    },
    removeTag(val) {
      if (val === '全选') {
        this.selectedArray = []
      }
    }
  }
}
</script>
