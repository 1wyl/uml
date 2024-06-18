<template>
  <div class="resource-page">
    <div class="resource-navbar">
      <el-button type="primary" icon="el-icon-picture" @click="redirectToImagePage">食品资源</el-button>
      <el-button type="primary" icon="el-icon-picture" @click="redirectToVideoPage">医药资源</el-button>
    </div>
    <div class="resource-list" :style="{ minHeight: listMinHeight }">
      <div class="image-list">
        <div v-for="(item, index) in imageResources" :key="index" class="image-item">
          <img :src="item.url" alt="Image" @click="viewImage(item)">
          <div class="image-description">{{ item.description }}</div>
          <el-button
  class="request-button"
  type="primary"
  @click="requestFood(item.fid)"
>
  请求
</el-button>

        </div>
      </div>
      <!-- <el-button class="fixed-add-button" type="primary" icon="el-icon-plus" @click="addImage"></el-button> -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { getAccessToken } from "@/utils/auth";
import { requestfood } from "@/api/source"; // 假设你的API函数在这个路径

export default {
  data() {
    return {
      imageResources: [],
      listMinHeight: 'calc(100vh - 120px)' // 设置最小高度
    };
  },
  created() {
    this.fetchImageResources();
  },
  methods: {
    fetchImageResources() {
      axios.get('/api/source/food', {
        headers: {
          'Authorization': 'Bearer ' + getAccessToken(),
        }
      })
      .then(response => {
        console.log('API response:', response.data); // Log the API response
        this.imageResources = response.data.data.map(image => ({
          fid: image.fid,
          url: 'http://localhost:8000/food/' + image.url,
          description: image.description ? image.description : '无描述'
        }));
        console.log('Formatted image resources:', this.imageResources); // Log the formatted image resources
        if (this.imageResources.length === 0) {
          this.listMinHeight = 'calc(100vh - 90px)'; // 没有图片时，设置最小高度为页面高度减去页面头部的高度
        } else {
          this.listMinHeight = 'auto'; // 有图片时，自动调整最小高度
        }
      })
      .catch(error => {
        console.error('Error fetching image resources:', error);
      });
    },
    addImage() {
      this.$router.push('/worker/addFood');
    },
    viewImage(item) {
      console.log('查看图片:', item.url);
      console.log('图片描述:', item.description);
    },
    redirectToImagePage() {
      this.$router.push('/service/food');
    },
    redirectToVideoPage() {
      this.$router.push('/service/medicine');
    },
    requestFood(fid) {
      requestfood(fid)
        .then(response => {
          this.$message.success('请求成功');
        })
        .catch(error => {
          this.$message.error('请求失败');
          console.error('Error requesting food:', error);
        });
    }
  }
};

</script>
<style>
.resource-page {
  background-color: #f4f6f8;
  min-height: 100vh;
  padding: 20px;
  position: relative;
}

.resource-navbar {
  display: flex;
  justify-content: flex-start;
  gap: 10px;
  margin-bottom: 20px;
}

.resource-list {
  background-color: #ffffff;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: relative;
}

.image-list {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  padding: 10px;
}

.image-item {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
  position: relative;
}

.image-item:hover {
  transform: scale(1.05);
}

.image-item img {
  width: 100%;
  height: auto;
  display: block;
}

.image-description {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  color: #ffffff;
  padding: 5px;
  box-sizing: border-box;
}

.request-button {
  position: absolute;
  top: 10px;
  right: 10px;
  z-index: 10;
  border-radius: 50%; /* 使按钮变成圆形 */
  padding: 0; /* 去除默认内边距 */
  width: 40px; /* 设置按钮宽度 */
  height: 40px; /* 设置按钮高度 */
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px; /* 调整字体大小以适应按钮大小 */
}


.fixed-add-button {
  position: absolute;
  bottom: 20px;
  right: 20px;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s;
}

.fixed-add-button:hover {
  background-color: #409EFF;
}

</style>