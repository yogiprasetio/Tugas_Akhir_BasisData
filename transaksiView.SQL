create tablespace PROJECTAKHIR
	datafile 'D:\Praktikum\Basdat\Modul5\projectakhir.dbf
	size 30M;

create user yogi_06926
	identified by yogi06926
	default tablespace PROJECTAKHIR
	quota 30M on PROJECTAKHIR;

grant all privileges to yogi_06926;
conn yogi_06926;

create table cabang_06926(
	kode_cabang number(3) not null,
	nama_cabang varchar2(50),
	no_telp number(12),
	alamat_cabang varchar2(100),
	constraint pk_cabang primary key (kode_cabang)
);

create tabel konsumen_06926(
	nama_konsumen varchar2(50),
	alamat_konsumen varchar2(200),
	gender varchar2(10),
	umur number(2),
	no_kontrak number(12),
	no_telp number(12),
	status_menikah varchar2(20),
	kode_cabang number(3),
	penagih varchar2(50),
	constraint pk_konsumen primary key(no_kontrak),
	constraint fk_kodecabang foreign key(kode_cabang) references from cabang_06926(kode_cabang)
);

create table pegawai_06926(
	nama_pegawai varchar2(50),
	NIK number(12),
	no_telp number(12),
	username varchar2(20),
	password varchar2(50),
	alamat varchar2(200),
	gender varchar2(10),
	kode_cabang number(3),
	status_karyawan varchar2(20),
	constraint pk_karyawan primary key(NIK),
	constraint fk_cbgkarywan foreign key(kode_cabang) references from cabang_06926(kode_cabang)
);

create table jabatan_069269(
	nama_jabatan varchar2(50),
	job_grade number(2),
	NIK number(12),
	kode_cabang number(3),
	constraint fk_jabatanpgwai foreign key(NIK) references from pegawai_06926(NIK),
	constraint fk_jabatancbg foreign key(kode_cabang) references from cabang_06926(kode_cabang)
);

create table unit_06926(
	MERK VARCHAR2(50),
 	TIPE VARCHAR2(50),
 	WARNA VARCHAR2(50),
 	GOLONGAN VARCHAR2(50),
 	PLAT_NOMER VARCHAR2(10) not null,
 	TAHUN NUMBER(4),
 	CC NUMBER(5),
 	NO_KONTRAK NUMBER(12),
	constraint pk_unit primary key(plat_nomer),
	constraint fk_unit foreign key(no_kontrak) references from konsumen_06926(no_kontrak)
);

create table RWYT_KONSUMEN_06926(
	DENDA NUMBER(9),
 	ANGSURAN NUMBER(9),
 	TERLAMBAT_HARI NUMBER(9),
 	POKOK_HUTANG NUMBER(9),
 	TGL_JT_TEMPO NUMBER(2),
 	TENOR NUMBER(3),
 	NO_KONTRAK NUMBER(12),
 	PLAT_NOMER VARCHAR2(10),
	constraint fk_rwytkons foreign key(no_kontrak) references from konsumen_06926(no_kontrak),
	constraint fk_rwytunit foreign key(plat_nomer) references from unit_06926(plat_nomer)
);