import { useState } from 'react';
import api from '../services/api';

function Login() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [mensagem, setMensagem] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/login', { email, senha });
      setMensagem("Login realizado! ✅");
      console.log(response.data);
    } catch (error) {
      setMensagem("Erro ao logar ❌");
      console.error(error);
    }
  };

  return (
    <div style={{ padding: '50px', textAlign: 'center' }}>
      <h2>Acesse sua Conta</h2>
      <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '10px', maxWidth: '300px', margin: 'auto' }}>
        <input 
          type="email" 
          placeholder="Seu e-mail" 
          value={email} 
          onChange={(e) => setEmail(e.target.value)} 
          style={{ padding: '10px' }}
        />
        <input 
          type="password" 
          placeholder="Sua senha" 
          value={senha} 
          onChange={(e) => setSenha(e.target.value)} 
          style={{ padding: '10px' }}
        />
        <button type="submit" style={{ padding: '10px', cursor: 'pointer' }}>ENTRAR</button>
      </form>
      <p>{mensagem}</p>
    </div>
  );
}

export default Login;