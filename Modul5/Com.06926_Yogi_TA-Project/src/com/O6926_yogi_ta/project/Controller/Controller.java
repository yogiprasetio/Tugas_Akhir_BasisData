package com.O6926_yogi_ta.project.Controller;

import java.util.ArrayList;
import com.O6926_yogi_ta.project.Object.*;
import com.O6926_yogi_ta.project.Connection.Connectiion;
import static com.O6926_yogi_ta.project.View.Form_update.NIK;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller {
    Connectiion connect; //iki gawe pendeklarasian
    
    public static ArrayList<Pegawai_06926>  arrPegawai;
    public static ArrayList<Konsumen_06926> arrKonsumen;
    public static ArrayList<Cabang_06926> arrCabang;
    public static ArrayList<Unit_06926> arrUnit;
    public static ArrayList<Riwayat> arrRiwyat;
    public static ArrayList<Jabatan> arrJabatan;
    public Controller() throws SQLException {
    this.connect = new Connectiion(); //nek iki gawe create objeknya
    this.arrPegawai = new ArrayList<>();
    getDataPegawai();
    this.arrKonsumen = new ArrayList<>();
    getDataKonsumen();
    this.arrCabang= new ArrayList<>();
    getDataCabang();
    this.arrUnit = new ArrayList<>();
    getDataUnit();
    this.arrRiwyat= new ArrayList<>();
    getDataRiwayat();
    this.arrJabatan = new ArrayList<>();
    getDataJabatan();
    this.setDetailKonsumen();
    }
    
    public ArrayList<Cabang_06926> getDataCabang() throws SQLException{
        this.arrCabang.clear();
        ResultSet rs = this.connect.GetData("select * from cabang_06926");
        while (rs.next()){
            String Nama = rs.getString("NAMA_CABANG");
            String Alamat_cabang = rs.getString("ALAMAT_CABANG");
            String No_telp = rs.getString("NO_TELP");
            Integer Kode_cabang = rs.getInt("kode_cabang");
            Cabang_06926 cabang = new Cabang_06926(Nama, Alamat_cabang,No_telp, Kode_cabang);
            this.arrCabang.add(cabang);
        }
        return this.arrCabang;
    }
    
    public ArrayList<Konsumen_06926> getDataKonsumen() throws SQLException{
        this.arrKonsumen.clear();
        ResultSet rs = this.connect.GetData("select * from konsumen_06926");
        while ( rs.next()){
            String Nama = rs.getString("Nama_konsumen");
            String Alamat = rs.getString("Alamat_konsumen");
            String gender = rs.getString("gender");
            Integer umur = rs.getInt("umur");
            String no_kontrak = rs.getString("no_kontrak");
            String no_telp = rs.getString("no_telp");
            String perniahan = rs.getString("status_menikah");
            String penagih = rs.getString("penagih");
            Konsumen_06926 konsumen = new Konsumen_06926(Nama, Alamat, perniahan, gender, umur, no_kontrak, no_telp, penagih);
            this.arrKonsumen.add(konsumen);
        }
        return this.arrKonsumen;
    }
    
    public ArrayList<Pegawai_06926> getDataPegawai() throws SQLException{
        this.arrPegawai.clear();
        ResultSet rs = this.connect.GetData("select * from pegawai_06926");
        while(rs.next()){
            String nama_pegawai = rs.getString("nama_pegawai");
            String nik = rs.getString("NIK");
            String no_telp = rs.getString("no_telp");
            String user_name = rs.getString("username");
            String password = rs.getString("password");
            String alamat_pegawai = rs.getString("alamat");
            String gender = rs.getString("gender");
            Integer kode_cabang = rs.getInt("kode_cabang");
            String status_karyawan = rs.getString("status_karyawan");
            Pegawai_06926 pegawai = new Pegawai_06926(nama_pegawai, nik, no_telp, user_name, password, alamat_pegawai, gender, kode_cabang, status_karyawan);
            this.arrPegawai.add(pegawai);
        }
        return this.arrPegawai;
    }
    
    public ArrayList<Jabatan> getDataJabatan() throws SQLException{
        this.arrJabatan.clear();
        ResultSet rs = this.connect.GetData("select * from jabatan_06926");
        while (rs.next()){
            String nama_jabatan = rs.getString("nama_jabatan");
            Integer job_grade = rs.getInt("job_grade");
            String NIK = rs.getString("NIK");
            Jabatan jabatan = new Jabatan(nama_jabatan, job_grade, NIK);
            this.arrJabatan.add(jabatan);   
        }
        return this.arrJabatan;
    }
    
    public ArrayList<Riwayat> getDataRiwayat() throws SQLException{
        this.arrRiwyat.clear();
        ResultSet rs = this.connect.GetData("select * from rwyt_konsumen_06926");
        while (rs.next()){
            Integer denda = rs.getInt("denda");
            Integer angsuran = rs.getInt("angsuran");
            Integer terlambat = rs.getInt("terlambat_hari");
            Integer pokok_hutang = rs.getInt("pokok_hutang");
            Integer tgl_jt_tempo = rs.getInt("tgl_jt_tempo");
            Integer tenor = rs.getInt("tenor");
            Riwayat riwayat = new Riwayat(denda, angsuran, terlambat, pokok_hutang, tgl_jt_tempo, tenor, 0);
            this.arrRiwyat.add(riwayat);
        }
        return this.arrRiwyat;
    }
    
    public ArrayList<Unit_06926> getDataUnit() throws SQLException{
        this.arrUnit.clear();
        ResultSet rs = this.connect.GetData("select * from unit_06926");
        while (rs.next()){
            String merk = rs.getString("merk");
            String tipe = rs.getString("tipe");
            String warna = rs.getString("warna");
            String golongan = rs.getString("golongan");
            String plat= rs.getString("plat_nomer");
            Integer tahun = rs.getInt("tahun");
            Integer Cc = rs.getInt("cc");
            Unit_06926 unit = new Unit_06926(merk, tipe, warna, golongan, plat, tahun, Cc);
            this.arrUnit.add(unit);
        }
        return this.arrUnit;
    }
    
    public void insertDataPegawai(int kode_cabang,String nama,String Username,String Password,String Alamat,String gender,String Status,String no_telp,String NIK){
        this.connect.ManipulasiData("INSERT INTO PEGAWAI_06926 VALUES('"+nama+"',"+NIK+","+no_telp+",'"+Username+"','"+Password+"','"+Alamat+"','"+gender+"',"+kode_cabang+",'"+Status+"')");
        }
    
    public void insertDataJabatan(String nama_jabatan,int job_grade,String NIK, int kode_cabang){
       this.connect.ManipulasiData("INSERT INTO JABATAN_06926 VALUES('"+nama_jabatan+"',"+job_grade+","+NIK+","+kode_cabang+")");
}
    
    public void deleteDataPegawai(String NIK){
        this.connect.ManipulasiData("DELETE FROM PEGAWAI_06926 WHERE NIK = "+NIK);
    }
    
    public void deleteDataJabatan(String NIK){
        this.connect.ManipulasiData("DELETE FROM JABATAN_06926 WHERE NIK = "+NIK);
    }
    
    
    public void updateDataPegawai(String NIK,String nama,String gender,String No_Telp,String Alamat){
        this.connect.ManipulasiData("UPDATE PEGAWAI_06926 SET NAMA_PEGAWAI = '"+nama+"', NO_TELP = "+No_Telp+",ALAMAT = '"+Alamat+"', GENDER = '"+gender+"' WHERE NIK = "+NIK);
    }
    
    public void setNamaPenagih(String NoKontrak, String Penagih){
        this.connect.ManipulasiData("UPDATE KONSUMEN_06926 SET PENAGIH = '"+Penagih+"' WHERE NO_KONTRAK = "+NoKontrak);
    }
    
    public void setDetailKonsumen(){
        this.connect.ManipulasiData("select a.nama_konsumen, a.no_kontrak, a.no_telp, a.penagih, a.alamat, b.plat_nomer, b.merk, b.tipe, b.golongan, c.denda, c.angsuran, c.tgl_jt_tempo, c.terlambat_hari from konsumen_06926 a join unit_06926 b on a.no_kontrak = b.no_kontrak join riwayat_konsumen_06926 c on a.no_kontrak = c.no_kontrak");
         }

    
}
