import java.util.LinkedList;
import java.util.Queue;
public class SearchAlgorithms {
    public static void BreadthFirstSearch(Graph graph, String source, String destination, Report report) {
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0);
        Queue<Vertex> vertices = new LinkedList<>();
        vertices.add(graph.Vertices.get(source));
        while (!vertices.isEmpty()) {
            Vertex v = vertices.poll();
            v.setKnown(true);
            for (Edge e : v.getAdjacents()) {
                if (e.getVertex().isKnown())
                    continue;
                report.time_counter++;
                e.getVertex().setPrev(v);
                e.getVertex().setDis(v.getDis() + e.getDistance());
                if (e.getVertex().getCity().getName().equals(destination))
                    return;
                vertices.add(e.getVertex());
                if (vertices.size() > report.space_counter)
                    report.space_counter = vertices.size();
            }
        }
    }

    public static void UniformCostSearch(Graph graph, String source, String destination, Report report) {
        PriorityQueue queue = new PriorityQueue(graph.Vertices.size());
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0.0);
        queue.add(graph.Vertices.get(source));
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            if (v.getCity().getName().equals(destination)) {
                return;
            }

            if (!v.isKnown()) {
                v.setKnown(true);
                for (int i = 0; i < v.getAdjacents().size(); i++) {
                    if (!v.getAdjacents().get(i).getVertex().isKnown()) {
                        if (v.getAdjacents().get(i).getDistance() + v.getDis() < v.getAdjacents().get(i).getVertex().getDis()) {
                            report.time_counter++;
                            v.getAdjacents().get(i).getVertex().setPrev(v);
                            v.getAdjacents().get(i).getVertex().setDis(v.getAdjacents().get(i).getDistance() + v.getDis());
                            if (v.getAdjacents().get(i).getVertex().getHeapIndex() == -1)
                                queue.add(v.getAdjacents().get(i).getVertex());
                            else
                                queue.modifyPosition(v.getAdjacents().get(i).getVertex());

                            if (report.space_counter < queue.size())
                                report.space_counter = queue.size();
                        }
                    }
                }
            }
        }
    }
    public static void A_Star(Graph graph, String source, String destination, Report report) {
        PriorityQueue queue = new PriorityQueue(graph.Vertices.size());
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0.0);
        queue.add(graph.Vertices.get(source));
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            if (v.getCity().getName().equals(destination)) {
                return;
            }

            if (!v.isKnown()) {
                v.setKnown(true);
                for (int i = 0; i < v.getAdjacents().size(); i++) {
                    if (!v.getAdjacents().get(i).getVertex().isKnown()) {
                        v.setHeuristic(getHeuristic(v.getAdjacents().get(i), graph.Vertices.get(destination)));
                        if (v.getAdjacents().get(i).getDistance() + v.getDis() < v.getAdjacents().get(i).getVertex().getDis()) {
                            report.time_counter++;
                            v.getAdjacents().get(i).getVertex().setPrev(v);
                            v.getAdjacents().get(i).getVertex().setDis(v.getAdjacents().get(i).getDistance() + v.getDis());
                            if (v.getAdjacents().get(i).getVertex().getHeapIndex() == -1)
                                queue.add(v.getAdjacents().get(i).getVertex());
                            else
                                queue.modifyPosition(v.getAdjacents().get(i).getVertex());

                            if (queue.size() > report.space_counter)
                                report.space_counter = queue.size();
                        }
                    }
                }
            }
        }
    }

    private static double getHeuristic(Edge edge, Vertex vertex) {
        return Math.abs((edge.getVertex().getCity().getY() - vertex.getCity().getY()) / (edge.getVertex().getCity().getX() - vertex.getCity().getX())) * 4.694;
    }

    public static boolean DepthFirstSearch(Graph graph, String source, String destination, Report report) {
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0);
        report.space_counter = 1;
        return DepthFirstSearch_(graph, source, destination, report);
    }

    public static boolean DepthFirstSearch_(Graph graph, String source, String destination, Report report) {
        if (source.equals(destination))
            return true;

        Vertex v = graph.Vertices.get(source);
        v.setKnown(true);
        graph.Vertices.get(source).setKnown(true);
        boolean found = false;
        for (Edge e : v.getAdjacents()) {
            if (e.getVertex().isKnown())
                continue;
            report.time_counter++;
            e.getVertex().setPrev(v);
            e.getVertex().setDis(v.getDis() + e.getDistance());
            if (e.getVertex().getCity().getName().equals(destination))
                return true;
            found = DepthFirstSearch_(graph, e.getVertex().getCity().getName(), destination, report);
        }
        return found;

    }

    public static boolean IterativeDeepingSearch(Graph graph, String source, String destination, Report report) {
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0);
        for (int i = 0; i < graph.Vertices.size(); i++) {
            if (DepthLimitedSearch_(graph, source, destination, i, report))
                return true;
        }
        return false;
    }

    public static void BidirectionalSearch(Graph graph, String source, String destination, Report report) {
        int max1;
        int max2 = max1 = 0;
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0);
        Queue<Vertex> queue1 = new LinkedList<>();
        Queue<Vertex> queue2 = new LinkedList<>();

        queue1.add(graph.Vertices.get(source));
        queue2.add(graph.Vertices.get(destination));


        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Vertex v1 = queue1.poll();
            Vertex v2 = queue2.poll();
            v1.setKnown(true);
            v2.setKnown(true);
            for (Edge e : v1.getAdjacents()) {
                if (e.getVertex().isKnown())
                    continue;
                if (!e.getVertex().isEnqueued()) {
                    report.time_counter++;
                    e.getVertex().setEnqueued(true);
                    e.getVertex().setPrev(v1);
                    e.getVertex().setDis(v1.getDis() + e.getDistance());
                    queue1.add(e.getVertex());
                    if (queue1.size() > max2)
                        max1 = queue1.size();
                }
            }
            for (Edge e : v2.getAdjacents()) {

                if (e.getVertex().isKnown2())
                    continue;
                if (!e.getVertex().isEnqueued2()) {
                    report.time_counter++;
                    e.getVertex().setEnqueued2(true);
                    v2.setPrev(e.getVertex());
                    v2.setDis(e.getDistance() + e.getVertex().getDis());
                    queue2.add(e.getVertex());
                    if (queue2.size() > max2)
                        max2 = queue2.size();
                }

            }
            for (Vertex v : graph.Vertices.values()) {
                if (v.isKnown() && v.isKnown2())
                    for (Edge e : v.getAdjacents())
                        if (e.getVertex() == v.getPrev()) {
                            v.setDis(e.getVertex().getDis() + e.getDistance());
                            return;
                        }
            }

        }
        report.space_counter = max2 + max1;
    }

    public static void BestFirstSearch(Graph graph, String source, String destination, Report report) {
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(0.0);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setPrev(null);
        }

        PriorityQueue priorityQueue = new PriorityQueue(graph.Vertices.size());
        priorityQueue.add(graph.Vertices.get(source));
        while (!priorityQueue.isEmpty()) {
            Vertex v = priorityQueue.poll();
            if (v == graph.Vertices.get(destination))
                break;
            if (v.isKnown())
                continue;
            v.setKnown(true);
            for (Edge e : v.getAdjacents()) {
                if (e.getVertex().isKnown())
                    continue;
                report.time_counter++;
                if (e.getVertex().isEnqueued()) {
                    e.getVertex().setPrev(v);
                    e.getVertex().setDis(e.getDistance() + v.getDis());
                    continue;
                }
                e.getVertex().setHeuristic(getHeuristic(e, graph.Vertices.get(destination)));
                priorityQueue.add(e.getVertex());
                e.getVertex().setEnqueued(true);
                e.getVertex().setPrev(v);
                e.getVertex().setDis(v.getDis() + e.getDistance());
                if (priorityQueue.size() > report.space_counter)
                    report.space_counter = priorityQueue.size();
            }
        }
    }

    private static boolean DepthLimitedSearch_(Graph graph, String source, String destination, int maxdepth, Report report) {
        if (maxdepth == 0 && source.equals(destination)) {
            return true;
        } else if (maxdepth > 0) {
            graph.Vertices.get(source).setKnown(true);
            for (Edge e : graph.Vertices.get(source).getAdjacents()) {
                if (e.getVertex().isKnown())
                    continue;
                e.getVertex().setPrev(graph.Vertices.get(source));
                e.getVertex().setDis(graph.Vertices.get(source).getDis() + e.getDistance());
                report.time_counter++;
                if (destination.equals(e.getVertex().getCity().getName()))
                    return true;
                if (DepthLimitedSearch_(graph, e.getVertex().getCity().getName(), destination, maxdepth - 1, report))
                    return true;
            }
        }
        return false;

    }

    public static boolean DepthLimitedSearch(Graph graph, String source, String destination, int maxdepth, Report report) {
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(source).setDis(0);

        return DepthLimitedSearch_(graph, source, destination, maxdepth, report);
    }
}