package com.example.kosmo_erp80.dto;

<<<<<<< HEAD
public class MemberDTO {
    public final String TAG = "MemberDTO";

=======
import java.lang.reflect.Member;

public class MemberDTO {
    public final String TAG = "MemberDTO";
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
    private String mem_id = null;
    private String mem_pw = null;
    private String mem_email = null;
    private String mem_name = null;
<<<<<<< HEAD

    public MemberDTO() {
        MemberDTO.getInstance();
    }

    public static MemberDTO getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static final MemberDTO instance = new MemberDTO();
    }

    public String getMem_id() {
        return mem_id;
    }
    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }
    public String getMem_pw() {
        return mem_pw;
    }
    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }
=======
    public MemberDTO(){
        MemberDTO.getInstance();
    }
    public static MemberDTO getInstance(){ return LazyHolder.instance;}
    private static class LazyHolder {
        private static final MemberDTO instance = new MemberDTO();
    }
    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
    public String getMem_email() {
        return mem_email;
    }

<<<<<<< HEAD
    public String getMem_name() {
        return mem_name;
    }
    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }
}
=======
    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }


}
>>>>>>> d968ed16f89bcdbf2db9385a3f3d1c4e2ff84af1
