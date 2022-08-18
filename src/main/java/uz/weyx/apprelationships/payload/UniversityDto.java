package uz.weyx.apprelationships.payload;

import lombok.Data;

@Data
public class UniversityDto {//ma'lumotlarni tashish uchun xizmat qiladi
    private String name;//univer name
    private String city;
    private String district;
    private String street;
}
