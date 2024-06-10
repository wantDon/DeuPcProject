    package com.example.demo.myprofile.controller;

    import com.example.demo.myprofile.myprofileDTO.userDTO;
    import com.example.demo.myprofile.myprofileService.passwordService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.Map;

    @RestController
    @RequestMapping("/myprofile")
    public class passwordController {

        @Autowired
        private passwordService passwordService;

        @Autowired
        public passwordController(passwordService passwordService ) {
            this.passwordService = passwordService;
        }

        @PostMapping("/update-password")
        public Map<String, Object> updatePassword(@RequestBody HashMap<String, Object> map) {
            String id = (String)map.get("userId");
            String pwd = (String)map.get("newPassword");
            if (id == null || pwd == null) {
                throw new IllegalArgumentException("User ID and password must be provided");
            }

            boolean isUpdated = passwordService.updatePassword(id, pwd);
            Map<String, Object> response = new HashMap<>();
            response.put("success", isUpdated);
            return response;
        }
    }
