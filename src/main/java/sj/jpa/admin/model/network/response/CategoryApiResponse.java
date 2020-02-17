package sj.jpa.admin.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {

    private Long id;

    private LocalDateTime createdAt;

    private String createdBy;

    private String title;

    private String type;

    private LocalDateTime updatedAt;

    private String updatedBy;

}