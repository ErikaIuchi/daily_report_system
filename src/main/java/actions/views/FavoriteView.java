package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいね情報について画面の入力値・出力値を扱うVIEWモデル
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteView {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * いいねした従業員
	 */
	private EmployeeView employee;

	/**
	 * いいねされた日報
	 */
	private ReportView report;

	/**
	 * 登録日時
	 */
	private LocalDateTime createdAt;

	/**
	 * 更新日時
	 */
	private LocalDateTime updatedAt;

}
