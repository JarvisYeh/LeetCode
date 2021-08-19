package Algorithm.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test417_CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] incoming = new int[numCourses];
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
		for (int[] edge : prerequisites) {
			incoming[edge[1]]++;
			graph.get(edge[0]).add(edge[1]);
		}

		int finished = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < numCourses; i++) {
			if (incoming[i] == 0) q.offer(i);
		}
		while (!q.isEmpty()) {
			for (int c = q.size(); c > 0; c--) {
				int curr = q.poll();
				finished++;
				for (int next : graph.get(curr)) {
					incoming[next]--;
					if (incoming[next] == 0) {
						q.offer(next);
					}
				}
			}
		}
		return finished == numCourses;
	}

}
