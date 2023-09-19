import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import TestApi from './Api/testApi';

function App() {

  const[email, setEmail] = useState("");
  const[password, setPassword] = useState("");

  const[signInEmail, setSignInEmail] = useState("");
  const[signInPassword, setSignInPassword] = useState("");
  const[signInNickName, setSignInNickName] = useState("");

  const[user, setUser] = useState("로그인 하세요");


  useEffect(()=> {

  },[user])



  const onChangeSignEmail = (e) => {
    setSignInEmail(e.target.value);
    console.log(signInEmail);
  }

  const onChangeSignPassword = (e) => {
    setSignInPassword(e.target.value);
    console.log(signInPassword);
  }

  const onChangeSignNickName = (e) => {
    setSignInNickName(e.target.value);
    console.log(signInNickName);
  }

  const onChangeLoginEmail = (e) => {
    setEmail(e.target.value);
    console.log(email);
  }

  const onChangeLoginPassword = (e) => {
    setPassword(e.target.value);
    console.log(password);
  }

  const onClickSignIn = async() => {
    try{
      const rsp = await TestApi.signup(signInEmail, signInPassword, signInNickName);
      if(rsp.data) {
        console.log(rsp.data);
      }
    }catch(error){
      console.log("회원가입 실패")
    }

  }

  const onClickLogin = async() => {
    try{
      const rsp = await TestApi.login(email, password);
      if(rsp.data) {
        console.log(rsp.data);
        setUser(email);
      }
    }catch(error){
      console.log("로그인 실패")
    }

  }

  const onClickLogOut = async() => {
    setUser("로그인 하세요");

  }
  return (
    <>
    <div>
      <h2>{user}</h2>
    </div>


    <div>
      <h3>회원가입</h3>
      <input type="text" placeholder='이메일' onChange={onChangeSignEmail}/>
      <input type="password" placeholder='비밀번호' onChange={onChangeSignPassword} />
      <input type="text" placeholder='닉네임' onChange={onChangeSignNickName}/>
      <button onClick={onClickSignIn}>회원가입</button>


      <h3>로그인</h3>
      <input type="text" placeholder='이메일' onChange={onChangeLoginEmail}/>
      <input type="password" placeholder='비밀번호' onChange={onChangeLoginPassword}/>
      <button onClick={onClickLogin}>로그인</button>
      <button onClick={onClickLogOut}>로그아웃</button>
    </div>

    
    </>
  );
}

export default App;
