package exemples_storage

import javax.swing.JFrame
import java.awt.EventQueue
import javax.swing.JTextField
import javax.swing.JButton
import javax.swing.JLabel
import java.awt.BorderLayout
import javax.swing.JPanel
import java.awt.FlowLayout
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.cloud.storage.Bucket
import com.google.firebase.cloud.StorageClient
import com.google.firebase.cloud.FirestoreClient
import java.nio.file.Paths
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.IOException
import java.nio.ByteBuffer
import java.io.ByteArrayInputStream
import javax.swing.ImageIcon
import java.io.File

class AgafarImatge : JFrame() {
	val nomIm = JTextField(25)
	val boto = JButton("Agafar")

	val foto = JLabel()
	var bucket: Bucket? = null

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setBounds(100, 100, 450, 300)
		setLayout(BorderLayout())

		val panell1 = JPanel(FlowLayout())
		panell1.add(nomIm)
		panell1.add(boto)
		getContentPane().add(panell1, BorderLayout.NORTH)

		getContentPane().add(foto, BorderLayout.CENTER)

		boto.addActionListener { agafar() }

		val serviceAccount = FileInputStream(
			"acces-a-dades-6e5a6-firebase-adminsdk-ei7uc-fcf7da56aa.json"
		)

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://acces-a-dades-6e5a6.firebaseio.com")
			.setStorageBucket("acces-a-dades-6e5a6.appspot.com").build()

		FirebaseApp.initializeApp(options)

		bucket = StorageClient.getInstance().bucket()

	}

	fun agafar() {
		println(nomIm.getText())
		val refBucket = StorageClient.getInstance().bucket()

		val localFile = File.createTempFile ("images", "jpg")
/*		
		riversRef.getFile(localFile)
			.addOnSuccessListener(
				new OnSuccessListener < FileDownloadTask . TaskSnapshot >() {
					@Override
					public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
						// Successfully downloaded data to local file
						// ...
					}
				}
			).addOnFailureListener(
				new OnFailureListener () {
					@Override
					public void onFailure(@NonNull Exception exception) {
						// Handle failed download
						// ...
					}
				}
			);
		File localFile = File . createTempFile ("images", "jpg");
		riversRef.getFile(localFile)
			.addOnSuccessListener(
				new OnSuccessListener < FileDownloadTask . TaskSnapshot >() {
					@Override
					public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
						// Successfully downloaded data to local file
						// ...
					}
				}
			).addOnFailureListener(
				new OnFailureListener () {
					@Override
					public void onFailure(@NonNull Exception exception) {
						// Handle failed download
						// ...
					}
				}
			);
 */
	}

}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		AgafarImatge().isVisible = true
	}
}
