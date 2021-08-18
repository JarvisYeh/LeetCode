package Algorithm.DFS;

import java.util.*;

public class Test332_ReconstructItinerary {
	// Method 1: all permutation with boolean array
	public List<String> findItineraryI(List<List<String>> tickets) {
		List<String> res = new ArrayList<>();
		// sort tickets first so that DFS process in accordance with lexical order
		tickets.sort((l1, l2) -> {
			if (l1.get(0).equals(l2.get(0))) {
				return l1.get(1).compareTo(l2.get(1));
			}
			return l1.get(0).compareTo(l2.get(0));
		});
		allPermutation(tickets, new boolean[tickets.size()], res);
		return res;
	}

	private boolean allPermutation(List<List<String>> tickets, boolean[] used, List<String> curr) {
		if (curr.size() == tickets.size() + 1) {
			return true;
		}


		for (int i = 0; i < tickets.size(); i++) {
			// if tickets[i] is used before, skip it
			if (used[i]) continue;
			// according to description, the itinerary has to start from JFK
			// so if curr.size() == 0, we need to use the first ticket, the start airport should be JFK
			if (curr.size() == 0 && tickets.get(i).get(0).equals("JFK")) {
				used[i] = true;
				curr.addAll(tickets.get(i));
				if (allPermutation(tickets, used, curr)) return true;
				curr.clear();
				used[i] = false;
			}
			// if it's not the first destination, constraint is that last dest = ticket[i].start
			else if (curr.size() != 0 && curr.get(curr.size() - 1).equals(tickets.get(i).get(0))) {
				used[i] = true;
				curr.add(tickets.get(i).get(1));
				if (allPermutation(tickets, used, curr)) return true;
				curr.remove(curr.size() - 1);
				used[i] = false;
			}
		}
		return false;
	}


	// Method 2: all permutation with hashmap
	public List<String> findItineraryII(List<List<String>> tickets) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (List<String> ticket : tickets) {
			List<String> dest = map.getOrDefault(ticket.get(0), new ArrayList<>());
			dest.add(ticket.get(1));
			map.put(ticket.get(0), dest);
		}

		// sort destinations of all airports according to lexical order
		// so that smaller lexical airport will be traversed first
		// and the smaller lexical order answer will return first
		for (List<String> dest : map.values()) {
//			Collections.sort(dest);
			dest.sort(Comparator.naturalOrder());
		}

		// initialize start airport
		List<String> res = new ArrayList<>();
		res.add("JFK");
		helper("JFK", map, res, tickets.size() + 1);
		return res;
	}

	private boolean helper(String start, HashMap<String, List<String>> map, List<String> res, int n) {
		if (res.size() == n) {
			return true;
		}

		List<String> destAirports = map.get(start);
		// if map doesn't have next destination for this start airport, it is a dead end
		// return false
		if (destAirports == null) return false;

		for (int i = 0; i < destAirports.size(); i++) {
			String dest = destAirports.get(i);
			destAirports.remove(i);
			res.add(dest);
			if (helper(dest, map, res, n)) return true; // if one answer found, early return it
			res.remove(res.size() - 1);
			destAirports.add(i, dest);
		}
		return false;
	}

	// Method 3: find Eulerian path
	// TC: O(E)
	// SC: O(V + E) for hashMap and queue
	public List<String> findItineraryIII(List<List<String>> tickets) {
		HashMap<String, Queue<String>> ticketMap = new HashMap<>();
		// <key = start airport, value = queue for destination airports>
		for (List<String> ticket : tickets) {
			String start = ticket.get(0);
			Queue<String> dest = ticketMap.getOrDefault(start, new PriorityQueue<>());
			dest.offer(ticket.get(1));
			ticketMap.put(start, dest);
		}

		List<String> res = new ArrayList<>();
		dfs("JFK", ticketMap, res);
		return res;
	}

	private void dfs(String start, HashMap<String, Queue<String>> ticketMap, List<String> res) {
		Queue<String> destQ = ticketMap.get(start);
		// traverse node with smaller lexical order first
		while (destQ != null && !destQ.isEmpty()) {
			dfs(destQ.poll(), ticketMap, res);
		}
		// after traversed all neighbors, traversed itself
		res.add(0, start);
	}


	public static void main(String[] args) {
		Test332_ReconstructItinerary t = new Test332_ReconstructItinerary();
		List<List<String>> tickets = new ArrayList<>();
		String[][] ts = new String[][]{{"JFK","SFO"},{"SFO","ATL"},{"ATL","JFK"},{"JFK","BEJ"}};
		for (String[] ticket : ts) {
			tickets.add(Arrays.asList(ticket));
		}
		System.out.println(t.findItineraryIII(tickets));
	}
}
