package Controller;

import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DetectionController {

    public static void main(String[] args) throws FileNotFoundException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        DetectionController DetectionController = new DetectionController();
        DetectionController.detectObjectOnImage();         
//        System.out.println(Core.NATIVE_LIBRARY_NAME);
//        System.out.println(Core.VERSION);
    }

    public String detectObjectOnImage() throws FileNotFoundException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //  load the COCO class labels our YOLO model was trained on
        Scanner scan = new Scanner(new FileReader("C:\\Users\\Chiquang\\Documents\\NetBeansProjects\\SearchFilmApp\\darknet\\data\\coco.names"));
        List<String> cocoLabels = new ArrayList<String>();
        while(scan.hasNextLine()) {
            cocoLabels.add(scan.nextLine());
        }

        //  load our YOLO object detector trained on COCO dataset
        Net dnnNet = Dnn.readNetFromDarknet("C:\\Users\\Chiquang\\Documents\\NetBeansProjects\\SearchFilmApp\\darknet\\cfg\\yolov3.cfg",
                "C:\\Users\\Chiquang\\Documents\\NetBeansProjects\\SearchFilmApp\\darknet\\cfg\\yolov3.weights");
        // YOLO on GPU:
        dnnNet.setPreferableBackend(Dnn.DNN_BACKEND_CUDA);
        dnnNet.setPreferableTarget(Dnn.DNN_TARGET_CUDA);

        // generate radnom color in order to draw bounding boxes
        Random random = new Random();
        ArrayList<Scalar> colors = new ArrayList<Scalar>();
        for (int i= 0; i < cocoLabels.size(); i++) {
            colors.add(new Scalar( new double[] {random.nextInt(255), random.nextInt(255), random.nextInt(255)}));
        }


        // load our input image
        Mat img = Imgcodecs.imread("C:\\Users\\Chiquang\\Documents\\NetBeansProjects\\SearchFilmApp\\darknet\\data\\kite.jpg", Imgcodecs.IMREAD_COLOR); // dining_table.jpg soccer.jpg baggage_claim.jpg
        //  -- determine  the output layer names that we need from YOLO
        // The forward() function in OpenCV’s Net class needs the ending layer till which it should run in the network.
        //  getUnconnectedOutLayers() vraca indexe za: yolo_82, yolo_94, yolo_106, (indexi su 82, 94 i 106) i to su poslednji layeri
        // u networku:
        List<String> layerNames = dnnNet.getLayerNames();
        List<String> outputLayers = new ArrayList<String>();
        for (Integer i : dnnNet.getUnconnectedOutLayers().toList()) {
            outputLayers.add(layerNames.get(i - 1));
        }
        HashMap<String, List> result = forwardImageOverNetwork(img, dnnNet, outputLayers);

        ArrayList<Rect2d> boxes = (ArrayList<Rect2d>)result.get("boxes");
        ArrayList<Float> confidences = (ArrayList<Float>) result.get("confidences");
        ArrayList<Integer> class_ids = (ArrayList<Integer>)result.get("class_ids");

       //Hạn chế tình trạng các hộp chồng chéo lên nhau.
        MatOfInt indices =  getBBoxIndicesFromNonMaximumSuppression(boxes,
                confidences);
        //Vẽ hộp cho vật thể
        img =  drawBoxesOnTheImage(img,
                indices,
                boxes,
                cocoLabels,
                class_ids,
                colors);
        HighGui.imshow("Test", img );
        HighGui.waitKey(10000);
        return null;
    }

    private HashMap<String, List> forwardImageOverNetwork(Mat img ,Net dnnNet,List<String> outputLayers) {
        // --We need to prepare some data structure  in order to store the data returned by the network  (ie, after Net.forward() call))
        // So, Initialize our lists of detected bounding boxes, confidences, and  class IDs, respectively
        // This is what this method will return:
        HashMap<String, List> result = new HashMap<String, List>();
        result.put("boxes", new ArrayList<Rect2d>());
        result.put("confidences", new ArrayList<Float>());
        result.put("class_ids", new ArrayList<Integer>());

        //Chia pĩxel cho ảnh đầu vào

        Mat blob_from_image = Dnn.blobFromImage(img, 1 / 255.0, new Size(416, 416), // Here we supply the spatial size that the Convolutional Neural Network expects.
                new Scalar(new double[]{0.0, 0.0, 0.0}), true, false);
        dnnNet.setInput(blob_from_image);

        List<Mat> outputs = new ArrayList<Mat>();

        // -- Finally, let pass forward throught network. The main work is done here:
        dnnNet.forward(outputs, outputLayers);


        for(Mat output : outputs) {
            //  loop over each of the detections. Each row is a candidate detection,
            System.out.println("Output.rows(): " + output.rows() + ", Output.cols(): " + output.cols());
            for (int i = 0; i < output.rows(); i++) {
                Mat row = output.row(i);
                List<Float> detect = new MatOfFloat(row).toList();
                List<Float> score = detect.subList(5, output.cols());
                int class_id = argmax(score); // index maximalnog elementa liste
                float conf = score.get(class_id);
                if (conf >= 0.5) {
                    int center_x = (int) (detect.get(0) * img.cols());
                    int center_y = (int) (detect.get(1) * img.rows());
                    int width = (int) (detect.get(2) * img.cols());
                    int height = (int) (detect.get(3) * img.rows());
                    int x = (center_x - width / 2);
                    int y = (center_y - height / 2);
                    Rect2d box = new Rect2d(x, y, width, height);
                    result.get("boxes").add(box);
                    result.get("confidences").add(conf);
                    result.get("class_ids").add(class_id);
                }
            }
        }
        return result;
    }
    /**
     Returns index of maximum element in the list
     */
    private  int argmax(List<Float> array){
    float max = array.get(0);
    int re = 0;
        for (int i = 1; i < array.size(); i++) {
        if (array.get(i) > max) {
            max = array.get(i);
            re = i;
        }
    }
        return re;
}

    private MatOfInt getBBoxIndicesFromNonMaximumSuppression(ArrayList<Rect2d> boxes,ArrayList<Float> confidences ) {
        MatOfRect2d mOfRect = new MatOfRect2d();
        mOfRect.fromList(boxes);
        MatOfFloat mfConfs = new MatOfFloat(Converters.vector_float_to_Mat(confidences));
        MatOfInt result = new MatOfInt();
        Dnn.NMSBoxes(mOfRect, mfConfs, (float)(0.6), (float)(0.5), result);
        return result;
    }

    private Mat drawBoxesOnTheImage(Mat img,MatOfInt indices,
                                    ArrayList<Rect2d> boxes,
                                    List<String> cocoLabels,
                                    ArrayList<Integer> class_ids,
                                    ArrayList<Scalar> colors) {
        //Scalar color = new Scalar( new double[]{255, 255, 0});
        List indices_list = indices.toList();
        for (int i = 0; i < boxes.size(); i++) {
            if (indices_list.contains(i)) {
                Rect2d box = boxes.get(i);
                Point x_y = new Point(box.x, box.y);
                Point w_h = new Point(box.x + box.width, box.y + box.height);
                Point text_point = new Point(box.x, box.y - 5);
                Imgproc.rectangle(img, w_h, x_y, colors.get(class_ids.get(i)), 1);
                String label = cocoLabels.get(class_ids.get(i));

                Imgproc.putText(img, label, text_point, Imgproc.FONT_HERSHEY_SIMPLEX, 1, colors.get(class_ids.get(i)), 2);
            }
        }
        return img;


    }


}