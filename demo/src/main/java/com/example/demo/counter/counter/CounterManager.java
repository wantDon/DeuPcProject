package com.example.demo.counter.counter;

import com.example.demo.counter.DTO.PaymentDTO;
import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.DTO.UserDTO;
import com.example.demo.counter.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.List;
import java.util.Random;

public class CounterManager {

    private TestMapper testMapper;

    @Autowired
    public CounterManager(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public void addUserTime(String userName, int addTime) {
        //유저의 시간을 추가해주는 함수이다.
        //1.유저를 찾는다
        UserDTO userDTO = testMapper.selectUser(userName);
        //2.시간을 더해주고
        int time = userDTO.getTime() + addTime;
        //3.유저를 업데이트 해준다.
        userDTO.setTime(time);
        testMapper.updateUser(userDTO);
    }

    public UserDTO selectUser(String userName) {
        UserDTO userDTO = testMapper.selectUser(userName);

        return userDTO;
    }

    public List<TimeDTO> selectNonUserTime() {
        return testMapper.selectNonUserTime();
    }

    public List<TimeDTO> selectUserTime() {
        return testMapper.selectUserTime();
    }

    //비회원 랜덤계정 생성 메소드
    public UserDTO makeNewRandomUser() {
        UserDTO randomUserDTO = new UserDTO(); // 변수명을 소문자로 변경
        UserDTO insertUserDTO = new UserDTO();
        Random random = new Random(); // Random 객체를 밖으로 이동하여 재사용

        while (true) {
            int randomNumber = random.nextInt(10000);
            String result = String.format("%04d", randomNumber);
            String nonMemberCode = "비회원-" + result;

            // 생성된 코드로 사용자 조회
            randomUserDTO = testMapper.selectUser(nonMemberCode);

            if (randomUserDTO == null) {
                // 사용자가 존재하지 않으면 해당 코드 반환+insert시키기

                insertUserDTO.setId(nonMemberCode);

                insertUserDTO.setGrade(1);
                testMapper.insertUser(insertUserDTO);
                break;
            }
        }

        return insertUserDTO;
    }

    public void addUserPws_pcnum(UserDTO userDTO, String pcnum) {
        //신규 유저를 아이디 기반으로 찾은뒤에 값을 변경하고 insert 시켜준다.
        UserDTO userDTO1 = testMapper.selectUser(userDTO.getId());//유저 db탐색
        userDTO1.setPwd(pcnum);
        testMapper.updateUser(userDTO1);
    }


    //요금표 관리 메소드=============================================================
    public List<TimeDTO> selectAllTime() {
        return testMapper.selectAllTime();//시간표를 조회한다.
    }

    public void insertTime(TimeDTO timeDTO) {
        testMapper.insertTime(timeDTO);//시간을 추가한다.
    }

    public void updateTime(TimeDTO timeDTO) {
        testMapper.updateTime(timeDTO);//시간을 수정한다.
    }

    public void deleteTime(TimeDTO timeDTO) {
        testMapper.deleteTime(timeDTO);//해당시간을 삭제한다.
    }

    //payment관련 메소드 ========================================
    public List<PaymentDTO> selectAllPayment(PaymentDTO paymentDTO) {
        return testMapper.selectAllPayment(paymentDTO);//시간표를 조회한다.
    }

    public void insertPayment(PaymentDTO paymentDTO) {
        testMapper.insertPayment(paymentDTO);//시간을 추가한다.
    }

    public void updatePayment(PaymentDTO paymentDTO) {
        testMapper.updatePayment(paymentDTO);//시간을 수정한다.
    }

    public void deletePayment(PaymentDTO paymentDTO) {
        testMapper.deletePayment(paymentDTO);
    }

}
