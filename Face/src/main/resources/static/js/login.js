// 切换选项卡
function switchTab(tab) {
    // 切换按钮状态
    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.classList.remove('active');
        if (btn.textContent.includes(tab === 'register' ? '注册' : '登录')) {
            btn.classList.add('active');
        }
    });

    // 切换表单显示
    document.getElementById('loginForm').classList.toggle('active', tab === 'login');
    document.getElementById('registerForm').classList.toggle('active', tab === 'register');
}

// 处理注册提交
async function handleRegister(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const errorEl = document.getElementById('registerError');
    errorEl.textContent = '';

    try {
        const response = await axios.post('/api/users/register', formData, {
            headers: {'Content-Type': 'multipart/form-data'},
            mode: 'cors'
        });
        console.log(response.data)
        if (response.data.code === 200) {
            alert('注册成功！');
            switchTab('login');
        } else {
            errorEl.textContent = response.data.msg || '注册失败';
        }
    } catch (error) {
        errorEl.textContent = error.response?.data?.error || '网络错误，请稍后重试';
    }
}

// 处理登录提交
async function handleLogin(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const errorEl = document.getElementById('loginError');
    errorEl.textContent = '';

    try {
        const response = await axios.post('/api/users/login', {
            phone: formData.get('phone'),
            password: formData.get('password')
        }, {
            headers: {'Content-Type': 'application/json'},
            mode: 'cors'
        });
        console.log(response.data)
        if (response.data.code === 200) {
            // 保存token示例：
            // localStorage.setItem('token', response.data.token);
            //储存用户id
            localStorage.setItem('id',response.data.data.id)
            window.location.href = 'index.html';
        } else {
            errorEl.textContent = response.data.msg || '登录失败';
        }
    } catch (error) {
        errorEl.textContent = error.response?.data?.error || '网络错误，请稍后重试';
    }
}