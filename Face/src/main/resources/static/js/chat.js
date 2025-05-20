const {createApp, ref, onMounted} = Vue;

class AiRequest {
    constructor(id, prompt) {
        this.id = id;
        this.prompt = prompt;

    }
};

createApp({
    setup() {
        const messages = ref([]);
        const newQuestion = ref('');
        const loading = ref(false);
        const id = ref(Date.now());

        // 从本地存储中加载历史记录
        onMounted(() => {
            const saved = localStorage.getItem('chatHistory');
            if (saved) {
                messages.value = JSON.parse(saved);
            }
            // 如果没有历史记录，则显示初始问候
            if (messages.value.length === 0) {
                addAnswer("您好");
            }
            scrollToBottom();
        });

        // 保存聊天记录
        function saveChatHistory() {
            localStorage.setItem('chatHistory', JSON.stringify(messages.value));
        }


        // 添加用户问题
        function addQuestion(text) {
            messages.value.push({type: 'question', text});
            saveChatHistory();
            scrollToBottom();
        }

        // 添加回答
        function addAnswer(text) {
            messages.value.push({type: 'answer', text});
            saveChatHistory();
            scrollToBottom();
        }

        // 滚动到底部
        function scrollToBottom() {
            setTimeout(() => {
                const chatDiv = document.getElementById('chat');
                chatDiv.scrollTop = chatDiv.scrollHeight;
            }, 0);
        }

        // 发送问题并获取回答（字逐渐显示）
        async function sendQuestion() {
            const question = newQuestion.value.trim();
            if (!question) return;
            addQuestion(question);
            newQuestion.value = '';
            loading.value = true;

            // 先插入一个空的回答项，用于逐字显示
            const answerIndex = messages.value.length;
            messages.value.push({type: 'answer', text: ''});
            scrollToBottom();


            try {
                const userId = localStorage.getItem('id')
                const request = new AiRequest(userId, question);
                const response = await fetch('/api/users/chat', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(request)
                });

                const data = await response.json();

                if (data.code === 200) {

                    for (let char of data.response) {
                        messages.value[answerIndex].text += char;
                        await new Promise((resolve) => setTimeout(resolve, 50));
                        scrollToBottom();
                    }
                } else {
                    addAnswer("抱歉，获取回答时出现问题。");
                }
                saveChatHistory();
            } catch (error) {
                console.error("Error:", error);
            } finally {
                loading.value = false;
            }
        }

        return {messages, newQuestion, sendQuestion, loading};
    }
}).mount('#app');