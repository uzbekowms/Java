import java.util.*;

public class Task2 {
    // Dijkstra's algorithm to find the shortest path between start and end nodes
     public static int dijkstra(Map<Integer, List<Edge>> graph, int start, int end, int n) {
        // PriorityQueue to store and process nodes based on the cost (ascending order)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, start}); // Initial node with cost 0
        int[] dist = new int[n + 1]; // Distance array to track the shortest distance to each node
        Arrays.fill(dist, Integer.MAX_VALUE);  // Initialize all distances to infinity
        dist[start] = 0; // Distance to the start node is 0

        while (!pq.isEmpty()) {
            int[] current = pq.poll();  // Get the node with the smallest cost
            int currentCost = current[0];
            int currentCity = current[1];

            // If we reach the destination, return the cost
            if (currentCity == end) {
                return currentCost;
            }

            // Skip processing if the current cost is greater than the recorded distance
            if (currentCost > dist[currentCity]) {
                continue;
            }

            // Iterate over all neighbors of the current city
            for (Edge edge : graph.getOrDefault(currentCity, new ArrayList<>())) {
                int newCost = currentCost + edge.cost; // Calculate the cost to the neighbor
                // If the new cost is less than the recorded distance, update and add to the queue
                if (newCost < dist[edge.neighbor]) {
                    dist[edge.neighbor] = newCost;
                    pq.add(new int[]{newCost, edge.neighbor});
                }
            }
        }

        return -1;  // If the destination is unreachable, return -1
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of test cases: ");
        int s = Integer.parseInt(scanner.nextLine().trim());

        // Process each test case
        for (int test = 0; test < s; test++) {
            // Build the graph for the current test case
            Map<String, Integer> cityIndex = new HashMap<>();
            Map<Integer, List<Edge>> graph = buildGraph(scanner, cityIndex);

            System.out.println("Number of queries: ");
            int r = Integer.parseInt(scanner.nextLine().trim());
            for (int query = 0; query < r; query++) {
                System.out.println("Source and destination city: ");
                String[] path = scanner.nextLine().trim().split("\\s+");
                String source = path[0];
                String destination = path[1];

                // Get indices of source and destination cities
                int start = cityIndex.get(source);
                int end = cityIndex.get(destination);

                // Calculate and print the minimum transportation cost
                System.out.println(dijkstra(graph, start, end, cityIndex.size()));
            }

            // Skip the blank line between test cases
            if (test < s - 1) {
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Builds the graph and city index mapping for the given input
    private static Map<Integer, List<Edge>> buildGraph(Scanner scanner, Map<String, Integer> cityIndex) {
        System.out.println("Number of cities: ");
        int n = Integer.parseInt(scanner.nextLine().trim()); // Number of cities
        Map<Integer, List<Edge>> graph = new HashMap<>(); // Adjacency list representation of the graph

        // Read each city's data
        for (int i = 1; i <= n; i++) {
            System.out.print("City name: ");
            String cityName = scanner.nextLine().trim();
            cityIndex.put(cityName, i); // Map city name to its index
            System.out.print("Number of neighbours: ");
            int p = Integer.parseInt(scanner.nextLine().trim());
            graph.put(i, new ArrayList<>()); // Initialize adjacency list for the city

            // Read all neighbors and their transportation costs
            for (int j = 0; j < p; j++) {
                System.out.print("Connections: ");
                String[] connection = scanner.nextLine().trim().split("\\s+");
                int neighbor = Integer.parseInt(connection[0]); // Neighbor city index
                int cost = Integer.parseInt(connection[1]);  // Cost to the neighbor
                graph.get(i).add(new Edge(neighbor, cost));// Add edge to the graph
            }
        }

        return graph;
    }

    // Record to represent an edge in the graph
    record Edge(int neighbor, int cost) {
    }
}
