<template>
    <div class="background-carousel" :style="{ backgroundImage: `url(${currentImage})` }">
      <div class="overlay">
        <div class="header">
          <span class="slogan">智慧养老社区</span>
        </div>
        <div class="auth-buttons">
          <el-button type="primary" @click="goToLogin">进入社区</el-button>
        </div>
        <div class="text-overlay" v-html="typedText"></div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentIndex: 0,
        texts: [
          "我感觉我的叶子掉光了。<br>——影片《困在时间里的父亲》（英国，法国，2020）",
          "把每一次交流和拥抱都当作最后的机会，在你彻底遗忘我之前。<br>——影片《依然爱丽丝》（英国，法国，2014）",
          "母亲永远爱我，只是她不记得了。<br>——《脐带》（中国大陆，2022）"
        ],
        images: [
          "/user_assets/image1.jpeg",
          "/user_assets/image2.jpeg",
          // "/user_assets/image3.jpeg"
        ],
        typedText: '',
        typingIndex: 0,
        intervalId: null
      };
    },
    computed: {
      currentText() {
        return this.texts[this.currentIndex];
      },
      currentImage() {
        return this.images[this.currentIndex];
      }
    },
    mounted() {
      this.startCarousel();
      this.typeWriterEffect();
    },
    beforeDestroy() {
      clearInterval(this.intervalId); // 清除定时器
    },
    methods: {
      startCarousel() {
        this.intervalId = setInterval(() => {
          this.currentIndex = (this.currentIndex + 1) % this.images.length;
          this.changeBackgroundImage();
          this.typeWriterEffect();
        }, 8000); // 每8秒切换一次
      },
      changeBackgroundImage() {
        const bgElement = document.querySelector('.background-carousel');
        if (bgElement) {
          bgElement.style.backgroundImage = `url(${this.currentImage})`;
        }
      },
      typeWriterEffect() {
        this.typedText = '';
        this.typingIndex = 0;
        clearInterval(this.typingInterval);
        this.typingInterval = setInterval(() => {
          if (this.typingIndex < this.currentText.length) {
            this.typedText += this.currentText.charAt(this.typingIndex);
            this.typingIndex++;
          } else {
            clearInterval(this.typingInterval);
          }
        }, 130); // 每130ms打一个字
      },
      goToLogin() {
        this.$router.push('/login'); // 跳转到 login 页面
      }
    }
  };
  </script>
  
  <style scoped>
  .background-carousel {
    position: relative;
    width: 100%;
    height: 100vh;
    background-size: cover;
    background-position: center;
    transition: background-image 1s ease-in-out;
  }
  
  .overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.5);
  }
  
  .header {
    position: absolute;
    top: 20px;
    left: 20px;
    color: white;
    font-size: 18px;
    font-weight: bold;
  }
  
  .auth-buttons {
    position: absolute;
    top: 20px;
    right: 20px;
  }
  
  .text-overlay {
    color: white;
    font-size: 24px;
    font-weight: bold;
    text-align: center;
    max-width: 80%;
    line-height: 1.5;
    white-space: pre-line; /* 保持换行符 */
  }
  </style>