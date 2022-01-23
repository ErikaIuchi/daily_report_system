package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FavoriteConverter;
import actions.views.FavoriteView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Favorite;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 *
 */
public class FavoriteService extends ServiceBase {

	/**
	 * いいねした人一覧表示用
	 * @param report
	 * @param page
	 * @return
	 */
	public List<FavoriteView> getMinePerPage(ReportView report, int page){

		List<Favorite> favorites = em.createNamedQuery(JpaConst.Q_FAV_GET_ALL_MINE, Favorite.class)
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
				.getResultList();
		return FavoriteConverter.toViewList(favorites);
	}

	/**
	 * いいねテーブルに登録する
	 * @param fv
	 */
	public void create(FavoriteView fv){
		LocalDateTime ldt = LocalDateTime.now();
		fv.setCreatedAt(ldt);
		fv.setUpdatedAt(ldt);
		createInternal(fv);
	}

	/**
	 * いいねを１件登録する
	 * @param fv
	 */
	private void createInternal(FavoriteView fv) {

		em.getTransaction().begin();
		em.persist(FavoriteConverter.toModel(fv));
		em.getTransaction().commit();
	}

	/**
	 * いいねがついているかどうか
	 */
	public long isFavorite(EmployeeView employee, ReportView report) {

		long count = (long)em.createNamedQuery(JpaConst.Q_FAV_COUNT_ALL_MINE, Long.class)
				.setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
				.getSingleResult();
		return count;
	}

	/**
	 * 指定した日報データについたいいねの件数を取得し、返却する
	 * @param report
	 * @return
	 */
	public long countAllMine(ReportView report) {

		long count = (long)em.createNamedQuery(JpaConst.Q_FAV_GET_ALL, Long.class)
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
				.getSingleResult();

		return count;
	}


}
