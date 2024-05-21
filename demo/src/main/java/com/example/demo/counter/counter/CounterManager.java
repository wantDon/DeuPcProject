package com.example.demo.counter.counter;

import com.example.demo.counter.DTO.UserDTO;
import com.example.demo.counter.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CounterManager {

    private TestMapper testMapper;

    @Autowired
    public CounterManager(TestMapper testMapper){
        this.testMapper = testMapper;
        System.out.println("Countermanager.생성자+");
        System.out.println(testMapper);
    }

    public void addUserTime(String userName,int addTime){
        //유저의 시간을 추가해주는 함수이다.
        //1.유저를 찾는다
        UserDTO userDTO = testMapper.selectUser(userName);
        //2.시간을 더해주고
        int time = userDTO.getTime() + addTime;
        //3.유저를 업데이트 해준다.
        userDTO.setTime(time);
        testMapper.updateUser(userDTO);

    }

}
