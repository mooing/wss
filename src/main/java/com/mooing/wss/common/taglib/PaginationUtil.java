/*
 * $Id: PaginationUtil.java 2463 2011-10-10 09:28:53Z zijing.zhang $ 
 *
 */
package com.mooing.wss.common.taglib;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mooing.wss.common.util.Pagination;

/**
 * 
 * Title: PaginationUtil Description:
 * 
 * @author kaiming.chi
 * 
 * @date 2013-11-29
 */
public final class PaginationUtil {

	private PaginationUtil() {
	}

	@SuppressWarnings("rawtypes")
	public static String pageCompoent(String controller, String action, Pagination page, Map<String, String> params) {
		StringBuilder builder = new StringBuilder();

		builder.append(PaginationUtil.pageLink(controller, action, page, params));
		builder.append("<span class=\"all\">共 ");
		builder.append(page.getTotalItemNum());
		builder.append(" 条记录</span>");

		return builder.toString();
	}

	/**
	 * 
	 * @param path
	 * @param controller
	 * @param action
	 * @param page
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String pageLink(String controller, String action, Pagination page, Map<String, String> params) {

		StringBuilder builder = new StringBuilder();
		List<PageLink> pageLinks = PageLink.buildLinks(page.getCurrentPageNum(), page.getTotalPageNum());

		for (PageLink link : pageLinks) {
			if (link.isDisplay()) {
				Map<String, String> result = Maps.newHashMap();

				if (link.isClickable()) {
					if (params != null) {
						result.putAll(params);
					}
					result.put("pnum", link.getNumber() + "");
					result.put("psize", page.getPageSize() + "");
					builder.append(buildHtmlLinK(UrlUtil.actionUrl(controller, action, result), link.getContent()));
				} else {
					builder.append("<span class=\"currPage\">");
					builder.append(link.getContent());
					builder.append("</span> ");
				}
			}
		}

		return builder.toString();

	}

	/**
	 * build html link.
	 * 
	 * @param url
	 * @param context
	 * @return
	 */
	public static String buildHtmlLinK(String url, String context) {

		StringBuilder builder = new StringBuilder();

		builder.append("<a href='");
		builder.append(url);
		builder.append("'>");
		builder.append(context);
		builder.append("</a>");

		return builder.toString();

	}

	/**
	 * 分页链接信息
	 * 
	 * 用于：生成分页具体link 包含的信息有：link内容、link是否能点击、link是否显示
	 */
	private static abstract class PageLink {
		protected int number;
		protected int current;
		protected int total;

		/**
		 * 唯一取得PageLink方法
		 * 
		 * @param now
		 * @param total
		 * @return
		 */
		static List<PageLink> buildLinks(int now, int total) {
			LinkedList<PageLink> pageLinks = Lists.newLinkedList();

			int preCount = 0;
			for (int number = now; number > 0; number--) {
				++preCount;
				if (preCount == UrlUtil.SIDE_SIZE + 1) {
					break;
				}
				pageLinks.addFirst(new NumberLink(number, now, total));
			}

			int nexCount = 0;
			for (int number = now + 1; number <= total; number++) {
				++nexCount;
				if (nexCount == UrlUtil.SIDE_SIZE) {
					break;
				}
				pageLinks.addLast(new NumberLink(number, now, total));
			}

			pageLinks.addFirst(new PreviousLink(now, total));
			pageLinks.addFirst(new FirstLink(now, total));

			pageLinks.addLast(new NextLink(now, total));
			pageLinks.addLast(new LastLink(now, total));

			return pageLinks;
		}

		/**
		 * protected ctor for children.
		 * 
		 * @param number
		 * @param current
		 * @param total
		 */
		protected PageLink(int number, int current, int total) {
			this.number = number;
			this.current = current;
			this.total = total;
		}

		/**
		 * 能否点击.
		 * 
		 * @return
		 */
		boolean isClickable() {
			return number != current;
		}

		int getNumber() {
			return this.number;
		}

		/**
		 * 链接内容.
		 * 
		 * @return
		 */
		abstract String getContent();

		/**
		 * 是否显示.
		 * 
		 * @return
		 */
		abstract boolean isDisplay();

		// =================具体Link====================

		private static class FirstLink extends PageLink {

			protected FirstLink(int current, int total) {
				super(1, current, total);
			}

			@Override
			String getContent() {
				return "首页";
			}

			@Override
			boolean isDisplay() {
				return current != 1;
			}
		}

		private static class LastLink extends PageLink {

			protected LastLink(int current, int total) {
				super(total, current, total);
			}

			@Override
			String getContent() {
				return "末页";
			}

			@Override
			boolean isDisplay() {
				return current != total;
			}
		}

		private static class NextLink extends PageLink {

			protected NextLink(int current, int total) {
				super(current + 1, current, total);
			}

			@Override
			String getContent() {
				return "下一页";
			}

			@Override
			boolean isDisplay() {
				return current != total;
			}
		}

		private static class PreviousLink extends PageLink {

			protected PreviousLink(int current, int total) {
				super(current - 1, current, total);
			}

			@Override
			String getContent() {
				return "上一页";
			}

			@Override
			boolean isDisplay() {
				return current != 1;
			}
		}

		private static class NumberLink extends PageLink {

			protected NumberLink(int number, int current, int total) {
				super(number, current, total);
			}

			@Override
			String getContent() {
				if (isClickable()) {
					return "[" + number + "]";
				} else {
					return number + "";
				}
			}

			@Override
			boolean isDisplay() {
				return true;
			}
		}

	}

}
